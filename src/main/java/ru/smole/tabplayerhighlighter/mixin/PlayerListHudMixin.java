package ru.smole.tabplayerhighlighter.mixin;

import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.hud.PlayerListHud;
import net.minecraft.client.network.PlayerListEntry;
import net.minecraft.text.Text;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import ru.smole.tabplayerhighlighter.TabPlayerHighlighter;

import java.util.Comparator;
import java.util.List;

@Mixin(PlayerListHud.class)
public class PlayerListHudMixin {
    
    @Unique
    private static final List<String> PLAYERS = TabPlayerHighlighter.CONFIG.players();
    @Unique
    private static final Comparator<PlayerListEntry> ENTRY_ORDERING = Comparator.comparingInt(entry -> PLAYERS.contains(entry.getProfile().getId().toString()) ? 0 : 1);
    
    @Shadow
    @Final
    private MinecraftClient client;
    
    @Inject(method = "getPlayerName", at = @At("RETURN"), cancellable = true)
    public void getPlayerName(PlayerListEntry entry, CallbackInfoReturnable<Text> cir) {
        Text displayName = cir.getReturnValue();
        if (PLAYERS.contains(entry.getProfile().getId().toString()))
            cir.setReturnValue(Text.of("§b[★]§r " + displayName.getString()));
    }
    
    @Inject(method = "collectPlayerEntries", at = @At("RETURN"), cancellable = true)
    public void collect(CallbackInfoReturnable<List<PlayerListEntry>> cir) {
        cir.setReturnValue(cir.getReturnValue().stream().sorted(ENTRY_ORDERING).toList());
    }
}
