# NoPumpkinBinding: For those using `KeepInventory`

NoPumpkinBinding is a Minecraft plugin. Born out of necessity, while playing on a session that has  the `keepInventory` game mode set to `true`, this tool keeps singleplayer and multiplayer enjoyable and free from frustrations resulting from trolls and mistakes.. 

**The Problem:** Wearing a pumpkin with `binding` in `keepInventory` results in the pumpkin becoming permanently bound with no method for removal. Typically, this can only be undone by either breaking the item or dying and losing your inventory. However, pumpkins are unbreakable, and you can't lose your inventory while `keepInventory` is active. Thus, users can be stuck with a pumpkin head indefinitely.

**The Solution:**
1. **Disable Binding Enchant:** Prevents the binding enchantment from being applied on a pumpkin.
2. **Command for Removal:** Registers a command, `getthispumpkinoffme`, to remove a pumpkin if you manage to encounter one created before the mod's installation.

## Supported Versions
NoPumpkinBinding supports **Minecraft 1.19.2**. Made for the Paper server. Tested on **Java 1.8**.

## Easy Installation
1. Download NoPumpkinBinding from **releases**.
2. Add to your **plugins** directory.

## Available Commands
- `getthispumpkinoffme` : To free yourself from a binding pumpkin.
- `nopumpkinbindingver` : To view the current version of the tool.

## Configuration
```yml
# Wish to see "NoPumpkinBinding Started!" at startup?
startup-message: true
# Prefer to drop the item instead of simply removing the enchantment?
drop: true
# Alternatively, want to break the item?
break: false
```

## License
NoPumpkinBinding has the Apache 2.0 License. Find it in the `LICENSE` file.
