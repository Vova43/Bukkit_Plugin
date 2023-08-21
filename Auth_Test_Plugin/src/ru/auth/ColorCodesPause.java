package ru.auth;


public class ColorCodesPause {

    public String FormatPause(String in) {
        String fp1 = in.replace("&0", "§0");
        String fp2 = fp1.replace("&1", "§1");
        String fp3 = fp2.replace("&2", "§2");
        String fp4 = fp3.replace("&3", "§3");
        String fp5 = fp4.replace("&4", "§4");
        String fp6 = fp5.replace("&5", "§5");
        String fp7 = fp6.replace("&6", "§6");
        String fp8 = fp7.replace("&7", "§7");
        String fp9 = fp8.replace("&8", "§8");
        String fp10 = fp9.replace("&9", "§9");
        String fp11 = fp10.replace("&a", "§a");
        String fp12 = fp11.replace("&b", "§b");
        String fp13 = fp12.replace("&c", "§c");
        String fp14 = fp13.replace("&d", "§d");
        String fp15 = fp14.replace("&e", "§e");
        String fp16 = fp15.replace("&f", "§f");
        
        String fp17 = fp16.replace("&k", "§k");
        String fp18 = fp17.replace("&l", "§l");
        String fp19 = fp18.replace("&m", "§m");
        String fp20 = fp19.replace("&n", "§n");
        String fp21 = fp20.replace("&o", "§o");
        String output = fp21.replace("&r", "§r");

        return output;
    }
    
    /*
    @EventHandler
    public void onPlayerInteract(PlayerInteractEvent event) {
        // получаем ссылку на игрока из события
        Player player = event.getPlayer();
        // получаем ссылку на кликнутый блок
        Block clicked = event.getClickedBlock();
        // если блок воздух - пропускаем выполнение дальнейшего кода
        if (clicked.isEmpty() || clicked.getType().isAir())
            return;

        // получаем ссылку на предмет в главной руке
        ItemStack stack = player.getInventory().getItemInMainHand();
        // проверяем, является ли предмет костью
        if (stack.getType() == Material.BONE) {
            // проверяем, имеет ли предмет ItemMeta
            // если у предмета не будет ItemMeta, но мы захотим получить имя предмета,
            // сервер выбросит NullPointerException
            if (stack.hasItemMeta()) {
                ItemMeta meta = stack.getItemMeta();
                // желательно сделать проверку, имеет ли предмет имя
                // заодно мы проверяем имя предмета
                if (meta.hasDisplayName()
                        && meta.getDisplayName().equalsIgnoreCase("§eШаманская кость")) {
                    spawnTamedWolf(player, clicked.getLocation());
                    player.sendMessage("§eВаш волк призван. Пора на охоту!");
                }
            }
        }
    }
    */
}