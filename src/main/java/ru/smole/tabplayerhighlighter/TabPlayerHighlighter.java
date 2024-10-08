package ru.smole.tabplayerhighlighter;

import net.fabricmc.api.ClientModInitializer;
import ru.smole.tabplayerhighlighter.config.TabConfig;

public class TabPlayerHighlighter implements ClientModInitializer {
    
    public final static TabConfig CONFIG = TabConfig.createAndLoad();
    
    @Override
    public void onInitializeClient() {
    }
}
