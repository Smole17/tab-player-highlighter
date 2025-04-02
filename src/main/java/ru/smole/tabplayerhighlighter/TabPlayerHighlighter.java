package ru.smole.tabplayerhighlighter;

import net.fabricmc.api.ClientModInitializer;
import ru.smole.tabplayerhighlighter.config.TabConfig;

public class TabPlayerHighlighter implements ClientModInitializer {
    public static final TabConfig CONFIG = TabConfig.createAndLoad();

    @Override
    public void onInitializeClient() {
        // Инициализация
    }
}