param([string]$ProjectRoot = (Split-Path $PSScriptRoot -Parent))
$ErrorActionPreference = 'Stop'
function Assert($condition, $message) { if (!$condition) { throw $message } }
function ReadJson($path) { Get-Content -LiteralPath (Join-Path $ProjectRoot $path) -Raw | ConvertFrom-Json }
$java = 'src/main/java/de/artemis/laboratoryblocks/common'
$generated = 'src/generated/resources'
$assets = "$generated/assets/laboratoryblocks"
$data = "$generated/data/laboratoryblocks"
$registry = Get-Content -LiteralPath (Join-Path $ProjectRoot "$java/registry/ModBlocks.java") -Raw
$blocks = @([regex]::Matches($registry, 'register\("([^"]+)"') | ForEach-Object { $_.Groups[1].Value })
$itemRegistry = Get-Content -LiteralPath (Join-Path $ProjectRoot "$java/registry/ModItems.java") -Raw
$items = @($blocks) + @([regex]::Matches($itemRegistry, 'register\("([^"]+)"') | ForEach-Object { $_.Groups[1].Value })
$palette = Get-Content -LiteralPath (Join-Path $ProjectRoot "$java/registry/ModBlockPalettes.java") -Raw
$pairs = [regex]::Matches($palette, 'new BlockPair\(ModBlocks\.(\w+), (?:ModBlocks\.(\w+)|null)\)')
$mapped = @{}
$counterparts = @{}
foreach ($pair in $pairs) {
    $original = $pair.Groups[1].Value.ToLower()
    Assert (!$mapped.ContainsKey($original)) "Duplicate palette entry: $original"
    $mapped[$original] = $true
    if ($pair.Groups[2].Success) {
        $dark = $pair.Groups[2].Value.ToLower()
        Assert (!$mapped.ContainsKey($dark)) "Duplicate palette entry: $dark"
        $mapped[$dark] = $true
        $counterparts["laboratoryblocks:$original"] = "laboratoryblocks:$dark"
    }
}
Assert ($mapped.Count -eq $blocks.Count) 'Inventory tabs do not cover every block'
foreach ($block in $blocks) { Assert ($mapped.ContainsKey($block)) "Missing inventory entry: $block" }
$toggles = @{}
foreach ($declaration in [regex]::Matches($registry, '(?s)public static final DeferredBlock<[^>]+> (\w+) = register\(.*?;')) {
    $target = [regex]::Match($declaration.Value, 'new (?:LaboratoryBlock|LaboratoryGlassBlock|RedstoneControlledLaboratoryBlock)\(ModBlocks\.(\w+)')
    if ($target.Success) { $toggles[$declaration.Groups[1].Value] = $target.Groups[1].Value }
}
foreach ($name in $toggles.Keys) {
    $expected = if ($name.StartsWith('GLOWING_')) { $name.Substring(8) } else { 'GLOWING_' + $name }
    Assert ($toggles[$name] -eq $expected -and $toggles[$expected] -eq $name) "Broken glowstone apply/remove pair: $name"
}

$recipes = @{}
foreach ($file in Get-ChildItem -LiteralPath (Join-Path $ProjectRoot "$data/recipe") -Filter '*.json') {
    $recipe = Get-Content -LiteralPath $file.FullName -Raw | ConvertFrom-Json
    $ingredients = switch ($recipe.type) {
        'minecraft:crafting_shaped' { @($recipe.key.PSObject.Properties | ForEach-Object { $_.Value }) }
        'minecraft:crafting_shapeless' { @($recipe.ingredients) }
        'minecraft:stonecutting' { @($recipe.ingredient) }
        default { throw "Unhandled recipe type: $($recipe.type)" }
    }
    foreach ($ingredient in $ingredients) {
        if ($ingredient -like 'laboratoryblocks:*') {
            Assert ($ingredient.Substring(17) -in $items) "Unknown ingredient $ingredient in $($file.Name)"
        }
    }
    $recipes[$file.BaseName] = @{ json = $recipe; ingredients = $ingredients }
}
# A fixed-point reachability check catches recipes that only unlock through circular crafting chains.
$reachable = @{}
$discDrops = ReadJson 'src/main/resources/data/minecraft/tags/item/creeper_drop_music_discs.json'
Assert (!$discDrops.replace) 'Music disc tag must preserve vanilla creeper drops'
foreach ($disc in $discDrops.values) {
    Assert ($disc -like 'laboratoryblocks:music_disc_*' -and $disc.Substring(17) -in $items) "Unknown creeper disc drop: $disc"
    $reachable[$disc] = $true
}
do {
    $before = $reachable.Count
    foreach ($entry in $recipes.Values) {
        $missing = @($entry.ingredients | Where-Object { $_ -like 'laboratoryblocks:*' -and !$reachable.ContainsKey($_) })
        if ($missing.Count -eq 0) { $reachable[$entry.json.result.id] = $true }
    }
} while ($reachable.Count -gt $before)
foreach ($item in $items) { Assert ($reachable.ContainsKey("laboratoryblocks:$item")) "No survival crafting path to $item" }

$rewards = @{}
foreach ($file in Get-ChildItem -LiteralPath (Join-Path $ProjectRoot "$data/advancement/recipes") -Recurse -Filter '*.json') {
    $advancement = Get-Content -LiteralPath $file.FullName -Raw | ConvertFrom-Json
    $criteria = @($advancement.criteria.PSObject.Properties.Name)
    foreach ($group in $advancement.requirements) {
        Assert ($group.Count -gt 0) "Empty requirement: $($file.Name)"
        foreach ($criterion in $group) { Assert ($criterion -in $criteria) "Unknown criterion: $criterion" }
    }
    foreach ($reward in $advancement.rewards.recipes) {
        Assert ($recipes.ContainsKey($reward.Substring(17))) "Unknown recipe reward: $reward"
        $rewards[$reward.Substring(17)] = $true
    }
}
foreach ($name in $recipes.Keys) { Assert ($rewards.ContainsKey($name)) "Recipe has no unlock advancement: $name" }

$pickaxe = (ReadJson "$generated/data/minecraft/tags/block/mineable/pickaxe.json").values
$axe = (ReadJson "$generated/data/minecraft/tags/block/mineable/axe.json").values
$doors = (ReadJson "$generated/data/minecraft/tags/block/doors.json").values
$trapdoors = (ReadJson "$generated/data/minecraft/tags/block/trapdoors.json").values
$lang = ReadJson "$assets/lang/en_us.json"
foreach ($block in $blocks) {
    Assert ("laboratoryblocks:$block" -in (@($pickaxe) + @($axe))) "Missing mining tag: $block"
    Assert ($null -ne $lang.PSObject.Properties["block.laboratoryblocks.$block"]) "Missing translation: $block"
    foreach ($relative in @("$assets/blockstates/$block.json", "$assets/items/$block.json", "$data/loot_table/blocks/$block.json")) {
        Assert (Test-Path -LiteralPath (Join-Path $ProjectRoot $relative)) "Missing resource: $relative"
    }
    if ($block -match '_trapdoor$') { Assert ("laboratoryblocks:$block" -in $trapdoors) "Missing trapdoor tag: $block" }
    elseif ($block -match '_door$') {
        Assert ("laboratoryblocks:$block" -in $doors) "Missing door tag: $block"
        $loot = ReadJson "$data/loot_table/blocks/$block.json"
        Assert ($loot.pools[0].entries[0].condition.state.half -eq 'lower') "Door drops twice: $block"
    }
}
foreach ($pair in $pairs | Where-Object { $_.Groups[2].Success }) {
    $original = $pair.Groups[1].Value.ToLower()
    $dark = $pair.Groups[2].Value.ToLower()
    Assert ($recipes.ContainsKey($original) -and $recipes.ContainsKey($dark)) "Missing parallel crafting recipe: $original / $dark"
    $countA = $recipes[$original].json.result.count
    $countB = $recipes[$dark].json.result.count
    Assert ($countA -eq $countB) "Palette recipes have different yields: $original / $dark"
    $expectedIngredients = @($recipes[$original].ingredients | ForEach-Object {
        if ($counterparts.ContainsKey($_)) { $counterparts[$_] } else { $_ }
    } | Sort-Object)
    $actualIngredients = @($recipes[$dark].ingredients | Sort-Object)
    if ($original -ne 'laboratory_block') {
        Assert (($expectedIngredients -join '|') -eq ($actualIngredients -join '|')) "Palette ingredients diverge: $original / $dark"
        Assert (($recipes[$original].json.pattern -join '|') -eq ($recipes[$dark].json.pattern -join '|')) "Palette crafting shapes diverge: $original / $dark"
    }
    $dyeName = if ($dark -eq 'dark_laboratory_block') { $dark } else { $dark + '_from_black_dye' }
    $dye = $recipes[$dyeName]
    Assert ($dye.json.result.count -eq 8 -and @($dye.ingredients | Where-Object { $_ -eq "laboratoryblocks:$original" }).Count -eq 8) "Unbalanced dye conversion: $dark"
    $reverse = $recipes[$original + '_from_white_dye']
    Assert ($null -ne $reverse -and $reverse.json.result.id -eq "laboratoryblocks:$original" -and
        $reverse.json.result.count -eq 8 -and @($reverse.ingredients | Where-Object { $_ -eq "laboratoryblocks:$dark" }).Count -eq 8 -and
        '#c:dyes/white' -in $reverse.ingredients) "Missing or unbalanced white dye conversion: $original"
}

# Count every occupied crafting slot, including repeated symbols, when auditing recoverable glowstone.
function GlowUnits($id) {
    if ($id -eq 'minecraft:glowstone_dust') { return 8 }
    if ($id -eq 'laboratoryblocks:glowstone_particles' -or $id -like 'laboratoryblocks:glowing_*') { return 1 }
    return 0
}
$craftingSignatures = @{}
$conversionEdges = @()
foreach ($name in $recipes.Keys) {
    $r = $recipes[$name].json
    $slots = @()
    if ($r.type -eq 'minecraft:crafting_shaped') {
        $rows = @($r.pattern | ForEach-Object {
            $row = $_
            (@($row.ToCharArray() | ForEach-Object {
                if ($_ -eq ' ') { '_' } else { $r.key.PSObject.Properties[[string]$_].Value }
            }) -join ',')
        })
        foreach ($row in $r.pattern) { foreach ($symbol in $row.ToCharArray()) {
            if ($symbol -ne ' ') { $slots += $r.key.PSObject.Properties[[string]$symbol].Value }
        } }
        $forward = $rows -join ';'
        $mirror = @($rows | ForEach-Object { $parts = $_ -split ','; [array]::Reverse($parts); $parts -join ',' }) -join ';'
        $signature = 'shaped:' + (@($forward, $mirror) | Sort-Object | Select-Object -First 1)
    } elseif ($r.type -eq 'minecraft:crafting_shapeless') {
        $slots = @($r.ingredients)
        $signature = 'shapeless:' + (($slots | Sort-Object) -join ',')
    } else {
        $slots = @($r.ingredient)
        $signature = $null
    }
    if ($signature) {
        Assert (!$craftingSignatures.ContainsKey($signature)) "Conflicting crafting recipes: $name / $($craftingSignatures[$signature])"
        $craftingSignatures[$signature] = $name
    }
    $inputGlow = 0
    foreach ($slot in $slots) { $inputGlow += GlowUnits $slot }
    $count = if ($null -ne $r.result.count) { [int]$r.result.count } else { 1 }
    Assert ((GlowUnits $r.result.id) * $count -le $inputGlow) "Recipe duplicates recoverable glowstone: $name"
    $blockInputs = @($slots | Where-Object { $_ -like 'laboratoryblocks:*' -and $_.Substring(17) -in $blocks })
    $distinctInputs = @($blockInputs | Sort-Object -Unique)
    if ($distinctInputs.Count -eq 1 -and $r.result.id -like 'laboratoryblocks:*' -and $r.result.id.Substring(17) -in $blocks) {
        $conversionEdges += @{ from = $distinctInputs[0]; to = $r.result.id; ratio = [double]$count / $blockInputs.Count; recipe = $name }
    }
}

# Ignore dyes and other consumables: even with unlimited supplies, a conversion cycle must not grow blocks.
$best = @{}
foreach ($block in $blocks) { $best["laboratoryblocks:$block"] = 1.0 }
for ($iteration = 0; $iteration -lt $blocks.Count; $iteration++) {
    $changed = $false
    foreach ($edge in $conversionEdges) {
        $candidate = $best[$edge.from] * $edge.ratio
        if ($candidate -gt $best[$edge.to] * (1.0 + 1e-9)) {
            $best[$edge.to] = $candidate
            $changed = $true
            Assert ($iteration -lt $blocks.Count - 1) "Block duplication cycle through recipe: $($edge.recipe)"
        }
    }
    if (!$changed) { break }
}

$advancements = @{}
foreach ($file in Get-ChildItem -LiteralPath (Join-Path $ProjectRoot 'src/main/resources/data/laboratoryblocks/advancement') -Filter '*.json') {
    $advancement = Get-Content -LiteralPath $file.FullName -Raw | ConvertFrom-Json
    $advancements[$file.BaseName] = $advancement
    $criteria = @($advancement.criteria.PSObject.Properties.Name)
    foreach ($group in $advancement.requirements) {
        foreach ($criterion in $group) { Assert ($criterion -in $criteria) "Unknown gameplay criterion $criterion" }
    }
    foreach ($criterion in $advancement.criteria.PSObject.Properties) {
        $c = $criterion.Value
        if ($c.trigger -eq 'minecraft:placed_block') {
            foreach ($id in $c.conditions.location.blocks) {
                Assert ($id.Substring(17) -in $blocks) "Unknown advancement block: $id"
                if ($counterparts.ContainsKey($id)) {
                    Assert ($counterparts[$id] -in $c.conditions.location.blocks) "Black palette excluded from $($file.Name)"
                }
            }
        }
        if ($c.trigger -eq 'minecraft:inventory_changed') {
            foreach ($item in $c.conditions.items) { Assert ($item.count.min -eq 1) "Advancement rejects stacks: $($file.Name)" }
        }
    }
}
foreach ($name in $advancements.Keys) {
    $visited = @{}
    $current = $name
    while ($current) {
        Assert (!$visited.ContainsKey($current)) "Advancement parent cycle at $current"
        Assert ($advancements.ContainsKey($current)) "Missing advancement parent: $current"
        $visited[$current] = $true
        $parent = $advancements[$current].parent
        $current = if ($parent) { $parent.Substring(17) } else { $null }
    }
}
$glowCoverage = $advancements['cold_white_glow'].criteria.placed_glowing_laboratory_block.conditions.location.blocks
foreach ($block in $blocks | Where-Object { $_.StartsWith('glowing_') }) {
    Assert ("laboratoryblocks:$block" -in $glowCoverage) "Glowing surface excluded from advancement: $block"
}
# Validate the generated cutout metadata as well as the models' referenced textures.
$modelCount = 0
foreach ($directory in @('src/main/resources/assets/laboratoryblocks/models', "$assets/models")) {
    foreach ($file in Get-ChildItem -LiteralPath (Join-Path $ProjectRoot $directory) -Recurse -Filter '*.json') {
        $json = Get-Content -LiteralPath $file.FullName -Raw | ConvertFrom-Json
        foreach ($texture in $json.textures.PSObject.Properties.Value | Where-Object { $_ -like 'laboratoryblocks:*' }) {
            $relative = 'textures/' + $texture.Substring(17) + '.png'
            Assert ((Test-Path (Join-Path $ProjectRoot "src/main/resources/assets/laboratoryblocks/$relative")) -or
                    (Test-Path (Join-Path $ProjectRoot "$assets/$relative"))) "Missing texture $texture in $($file.Name)"
        }
        if ($file.Directory.Name -eq 'block' -and $file.BaseName -match '(laboratory_(door|trapdoor)_(bottom|top|open)|laboratory_glass_inventory)') {
            Assert ($json.render_type -eq 'minecraft:cutout') "Lost cutout rendering in $($file.Name)"
        }
        $modelCount++
    }
}
$musicTags = (ReadJson 'src/main/resources/data/minecraft/tags/item/music_discs.json').values
$sounds = ReadJson 'src/main/resources/assets/laboratoryblocks/sounds.json'
$notices = Get-Content -LiteralPath (Join-Path $ProjectRoot 'src/main/resources/THIRD_PARTY_NOTICES.md') -Raw
foreach ($track in @('exploration', 'pondering_the_cosmos')) {
    $disc = "music_disc_$track"
    Assert ("laboratoryblocks:$disc" -in $musicTags -and "laboratoryblocks:$disc" -in $discDrops.values) "Missing disc tags: $disc"
    Assert ($lang.PSObject.Properties["item.laboratoryblocks.$disc"].Value -eq 'Music Disc') "Missing disc name: $disc"
    Assert ($lang.PSObject.Properties["item.laboratoryblocks.$disc.desc"].Value) "Missing artist credit: $disc"
    $song = ReadJson "src/main/resources/data/laboratoryblocks/jukebox_song/$track.json"
    Assert ($song.description.translate -eq "item.laboratoryblocks.$disc.desc") "Wrong song description: $track"
    Assert ($song.sound_event -eq "laboratoryblocks:music_disc.$track") "Wrong song sound event: $track"
    Assert ($song.comparator_output -ge 1 -and $song.comparator_output -le 15) "Invalid comparator output: $track"
    $sound = $sounds.PSObject.Properties["music_disc.$track"].Value.sounds
    Assert ($sound.Count -eq 1 -and $sound[0].stream -eq $true -and $sound[0].name -eq "laboratoryblocks:records/$track") "Music must use streamed record audio: $track"
    $itemModel = ReadJson "$assets/items/$disc.json"
    Assert ($itemModel.model.model -eq "laboratoryblocks:item/$disc") "Missing disc item model: $disc"
    Assert (Test-Path -LiteralPath (Join-Path $ProjectRoot "$assets/models/item/$disc.json")) "Missing flat disc model: $disc"
    Assert ($notices.Contains("sounds/records/$track.ogg")) "Missing third-party notice: $track"
    $audio = [IO.File]::ReadAllBytes((Join-Path $ProjectRoot "src/main/resources/assets/laboratoryblocks/sounds/records/$track.ogg"))
    $offset = 0; $sampleRate = 0; $lastSample = 0L; $channels = 0
    while ($offset -lt $audio.Length) {
        Assert ([Text.Encoding]::ASCII.GetString($audio, $offset, 4) -eq 'OggS') "Invalid Ogg page: $track"
        $granule = [BitConverter]::ToInt64($audio, $offset + 6)
        if ($granule -gt $lastSample) { $lastSample = $granule }
        $segments = [int]$audio[$offset + 26]; $body = $offset + 27 + $segments
        if ($offset -eq 0) {
            Assert ($audio[$body] -eq 1 -and [Text.Encoding]::ASCII.GetString($audio, $body + 1, 6) -eq 'vorbis') "Record is not Ogg Vorbis: $track"
            $channels = $audio[$body + 11]; $sampleRate = [BitConverter]::ToUInt32($audio, $body + 12)
        }
        $size = 0
        for ($segment = 0; $segment -lt $segments; $segment++) { $size += $audio[$offset + 27 + $segment] }
        $offset = $body + $size
    }
    Assert ($channels -eq 1 -and $sampleRate -gt 0) "Record must be mono for positional playback: $track"
    Assert ([Math]::Abs($song.length_in_seconds - [double]$lastSample / $sampleRate) -lt 0.01) "Jukebox duration does not match audio: $track"
}
$discChestTables = @{ exploration = 'chests/abandoned_mineshaft'; pondering_the_cosmos = 'chests/end_city_treasure' }
foreach ($track in $discChestTables.Keys) {
    $modifier = ReadJson "src/generated/resources/data/laboratoryblocks/loot_modifiers/music_disc_$track.json"
    Assert ($modifier.type -eq 'neoforge:add_table') "Disc loot must append to existing chest loot: $track"
    Assert ($modifier.condition.type -eq 'neoforge:loot_table_id' -and $modifier.condition.loot_table_id -eq "minecraft:$($discChestTables[$track])") "Wrong disc chest target: $track"
    Assert ($modifier.table -eq "laboratoryblocks:inject/music_disc_$track") "Wrong injected disc table: $track"
    $loot = ReadJson "src/generated/resources/data/laboratoryblocks/loot_table/inject/music_disc_$track.json"
    Assert ($loot.pools.Count -eq 1 -and $loot.pools[0].rolls -eq 1) "Disc loot must roll once: $track"
    Assert ($loot.pools[0].condition.type -eq 'minecraft:random_chance' -and [Math]::Abs($loot.pools[0].condition.chance - 0.1) -lt 0.00001) "Disc loot must have a 10% chance: $track"
    Assert ($loot.pools[0].entries.Count -eq 1 -and $loot.pools[0].entries[0].name -eq "laboratoryblocks:music_disc_$track") "Wrong disc in chest loot: $track"
}
Write-Output "Verified $($blocks.Count) blocks, $($recipes.Count) recipes and unlocks, $($advancements.Count) gameplay advancements, and $modelCount models: crafting balance, palette parity, loot, tags, translations, inventory coverage, advancements, textures, cutout rendering, and both streamed mono music discs with exact song durations, credits, and additive chest loot."
