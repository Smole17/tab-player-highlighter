package ru.smole.tabplayerhighlighter.config;

import io.wispforest.owo.config.annotation.Config;
import io.wispforest.owo.config.annotation.RestartRequired;

import java.util.List;

@Config(name = "tabplayerhighlighter", wrapperName = "TabConfig")
public class TabConfigModel {
    
    @RestartRequired
    public List<String> players = List.of("UUID");
}
