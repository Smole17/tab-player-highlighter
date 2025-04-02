package ru.smole.tabplayerhighlighter.config;

import io.wispforest.owo.config.annotation.Config;
import io.wispforest.owo.config.annotation.Modmenu;
import java.util.List;

@Config(name = "tabplayerhighlighter", wrapperName = "TabConfig")
@Modmenu(modId = "tabplayerhighlighter")
public class TabConfigModel {
    public List<String> players = List.of("// Напишите тут UUID игрока!");
    public String apiUrl = "https://kpbspm.del1t.me/assets/img/apitph/roleizdiscord.php"; // Добавляем URL для API
}