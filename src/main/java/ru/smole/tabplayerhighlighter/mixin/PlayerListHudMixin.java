package ru.smole.tabplayerhighlighter.mixin;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
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
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import ru.smole.tabplayerhighlighter.TabPlayerHighlighter;

import java.lang.reflect.Type;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.*;
import java.util.concurrent.CompletableFuture;

@Mixin(PlayerListHud.class)
public class PlayerListHudMixin {
    @Unique
    private static final List<String> PLAYERS = TabPlayerHighlighter.CONFIG.players();

    @Unique
    private static final Map<UUID, String> ROLES = new HashMap<>();

    @Unique
    private static final Map<String, String> ROLE_IDS = Map.ofEntries(
            Map.entry("502476489598697472", "prez"), //Президент
            Map.entry("1140378180323115119", "vice"), // Вице-президент
            Map.entry("1093933276391014502", "specsyd"), // Спец судья
            Map.entry("1093995524497084576", "cyd"), // судья
            Map.entry("1171735196954009662", "min"), // Глава Минюста
            Map.entry("654326849236762635", "detec"), // Глава Детективов
            Map.entry("760547107739664414", "bank"), // Глава банка
            Map.entry("1194056895242182726", "kazna"), // Казначей
            Map.entry("1001512535201157343", "fsb"), // Глава ФСБ
            Map.entry("841693391787655209", "spawn"), // Главный по спавну
            Map.entry("658170837819719740", "ad"), // Главный по аду
            Map.entry("676079168697401370", "end"), // Главный по энду
            Map.entry("1207723829577654282", "gid"), // Глава гидов
            Map.entry("1354331070190846063", "test_detect"),
            Map.entry("1354331020744196187", "test_bank"),
            Map.entry("1354394105156866150", "test_end"),
            Map.entry("1354394074290847755", "test_ad")

    );

    @Unique
    private static final Map<String, String> ROLE_SYMBOLS = Map.ofEntries(
            Map.entry("502476489598697472", "\uE000"), //Президент
            Map.entry("1140378180323115119", "\uE001"), // Вице-президент
            Map.entry("1093933276391014502", "\uE002"), // Спец судья
            Map.entry("1093995524497084576", "\uE003"), // судья
            Map.entry("1171735196954009662", "\uE004"), // Глава Минюста
            Map.entry("654326849236762635", "\uE005"), // Глава Детективов
            Map.entry("760547107739664414", "\uE006"), // Глава банка
            Map.entry("1194056895242182726", "\uE007"), // Казначей
            Map.entry("1001512535201157343", "\uE008"), // Глава ФСБ
            Map.entry("841693391787655209", "\uE009"), // Главный по спавну
            Map.entry("658170837819719740", "\uE00A"), // Главный по аду
            Map.entry("676079168697401370", "\uE00B"), // Главный по энду
            Map.entry("1207723829577654282", "\uE00C"), // Глава гидов
            Map.entry("1354331070190846063", "\uE001"),
            Map.entry("1354331020744196187", "\uE002"),
            Map.entry("1354394105156866150", "\uE003"),
            Map.entry("1354394074290847755", "\uE004")

    );

    @Unique
    private static final Map<String, String> ROLE_COLORS = Map.ofEntries(
            Map.entry("502476489598697472", "§5"), //Президент
            Map.entry("1140378180323115119", "§d"), // Вице-президент
            Map.entry("1093933276391014502", "§6"), // Спец судья
            Map.entry("1093995524497084576", "§e"), // судья
            Map.entry("1171735196954009662", "§5"), // Глава Минюста
            Map.entry("654326849236762635", "§3"), // Глава Детективов
            Map.entry("760547107739664414", "§a"), // Глава банка
            Map.entry("1194056895242182726", "§6"), // Казначей
            Map.entry("1001512535201157343", "§b"), // Глава ФСБ
            Map.entry("841693391787655209", "§2"), // Главный по спавну
            Map.entry("658170837819719740", "§c"), // Главный по аду
            Map.entry("676079168697401370", "§5"), // Главный по энду
            Map.entry("1207723829577654282", "§9"), // Глава гидов
            Map.entry("1354331070190846063", "§3"),
            Map.entry("1354331020744196187", "§a"),
            Map.entry("1354394105156866150", "§5"),
            Map.entry("1354394074290847755", "§c")
    );

    @Unique
    private static final Map<String, String> ROLE_ADD = Map.ofEntries(
            Map.entry("502476489598697472", " Президент"), //Президент
            Map.entry("1140378180323115119", " Вице-президент"), // Вице-президент
            Map.entry("1093933276391014502", " Спец судья"), // Спец судья
            Map.entry("1093995524497084576", " Судья"), // судья
            Map.entry("1171735196954009662", " Гл Минюста"), // Глава Минюста
            Map.entry("654326849236762635", " Гл Детективов"), // Глава Детективов
            Map.entry("760547107739664414", " Гл банка"), // Глава банка
            Map.entry("1194056895242182726", " Казначей"), // Казначей
            Map.entry("1001512535201157343", " Гл ФСБ"), // Глава ФСБ
            Map.entry("841693391787655209", " Гл спавна"), // Главный по спавну
            Map.entry("658170837819719740", " Гл эда"), // Главный по аду
            Map.entry("676079168697401370", " Гл энда"), // Главный по энду
            Map.entry("1207723829577654282", " Гл гидов"), // Глава гидов
            Map.entry("1354331070190846063", " Тест Гл детективов"),
            Map.entry("1354331020744196187", " Тест Гл банка"),
            Map.entry("1354394105156866150", " Тест Гл энда"),
            Map.entry("1354394074290847755", " Тест Гл ада")
    );

    @Unique
    private static final Map<String, Integer> ROLE_PRIORITY = Map.ofEntries(
            Map.entry("prez", 1), //Президент
            Map.entry("vice", 2), // Вице-президент
            Map.entry("specsyd", 3), // Спец судья
            Map.entry("cyd", 4), // судья
            Map.entry("min", 5), // Глава Минюста
            Map.entry("detec", 6), // Глава Детективов
            Map.entry("bank", 7), // Глава банка
            Map.entry("kazna", 8), // Казначей
            Map.entry("fsb", 9), // Глава ФСБ
            Map.entry("spawn", 10), // Главный по спавну
            Map.entry("ad",11), // Главный по аду
            Map.entry("end", 12), // Главный по энду
            Map.entry("gid", 13), // Глава гидов
            Map.entry( "test_detect", 14),
            Map.entry("test_bank", 15),
            Map.entry("test_end", 16),
            Map.entry("test_ad", 17)
    );

    @Shadow @Final
    private MinecraftClient client;

    @Unique
    private boolean firstRender = true;

    @Unique
    private UUID parseUUID(String uuidString) {
        try {
            if (uuidString.contains("-")) {
                return UUID.fromString(uuidString);
            }
            if (uuidString.length() == 32) {
                String formatted = uuidString.replaceFirst(
                        "(\\p{XDigit}{8})(\\p{XDigit}{4})(\\p{XDigit}{4})(\\p{XDigit}{4})(\\p{XDigit}+)",
                        "$1-$2-$3-$4-$5");
                return UUID.fromString(formatted);
            }
        } catch (IllegalArgumentException e) {
            System.out.println("[TabHighlighter] Failed to parse UUID: " + uuidString);
        }
        return null;
    }

    @Inject(method = "getPlayerName", at = @At("RETURN"), cancellable = true)
    public void getPlayerName(PlayerListEntry entry, CallbackInfoReturnable<Text> cir) {
        UUID playerUUID = entry.getProfile().getId();
        String playerName = cir.getReturnValue().getString();

        if (PLAYERS.contains(playerUUID.toString())) {
            cir.setReturnValue(Text.of("§b[★]§r " + playerName));
            return;
        }

        String discordRoleId = ROLES.get(playerUUID);
        if (discordRoleId != null) {
            // Используем discordRoleId напрямую для всех мап
            String symbol = ROLE_SYMBOLS.getOrDefault(discordRoleId, "");
            String color = ROLE_COLORS.getOrDefault(discordRoleId, "");
            String add = ROLE_ADD.getOrDefault(discordRoleId, "");
            if (!symbol.isEmpty() || !add.isEmpty()) {
                cir.setReturnValue(Text.of(color + "[" + symbol + add + "]§r " + playerName));
            }
        }
    }

    @Inject(method = "collectPlayerEntries", at = @At("RETURN"), cancellable = true)
    private void sortPlayerEntries(CallbackInfoReturnable<List<PlayerListEntry>> cir) {
        List<PlayerListEntry> original = cir.getReturnValue();
        if (original == null || original.isEmpty()) return;

        PlayerListEntry selfEntry = getSelfEntry(original);
        if (selfEntry == null) {
            selfEntry = client.getNetworkHandler().getPlayerListEntry(client.player.getUuid());
            if (selfEntry != null && !original.contains(selfEntry)) {
                original = new ArrayList<>(original);
                original.add(selfEntry);
            }
        }

        List<PlayerListEntry> sorted = new ArrayList<>(original);
        sorted.sort((e1, e2) -> {
            if (e1.getProfile().getId().equals(client.player.getUuid())) return -1;
            if (e2.getProfile().getId().equals(client.player.getUuid())) return 1;

            int priorityCompare = compareByPriority(e1, e2);
            if (priorityCompare != 0) return priorityCompare;

            return e1.getProfile().getName().compareToIgnoreCase(e2.getProfile().getName());
        });

        if (sorted.size() > 555) {
            sorted = sorted.subList(0, 555);
        }

        cir.setReturnValue(sorted);
    }

    @Unique
    private PlayerListEntry getSelfEntry(List<PlayerListEntry> entries) {
        UUID selfUuid = client.player.getUuid();
        return entries.stream()
                .filter(e -> e.getProfile().getId().equals(selfUuid))
                .findFirst()
                .orElse(null);
    }

    @Unique
    private int compareByPriority(PlayerListEntry e1, PlayerListEntry e2) {
        UUID uuid1 = e1.getProfile().getId();
        UUID uuid2 = e2.getProfile().getId();

        boolean isConfig1 = PLAYERS.contains(uuid1.toString());
        boolean isConfig2 = PLAYERS.contains(uuid2.toString());
        if (isConfig1 != isConfig2) return isConfig1 ? -1 : 1;

        String discordRoleId1 = ROLES.get(uuid1);
        String discordRoleId2 = ROLES.get(uuid2);
        if (discordRoleId1 != null || discordRoleId2 != null) {
            String role1 = discordRoleId1 != null ? ROLE_IDS.getOrDefault(discordRoleId1, "") : "";
            String role2 = discordRoleId2 != null ? ROLE_IDS.getOrDefault(discordRoleId2, "") : "";
            int p1 = !role1.isEmpty() ? ROLE_PRIORITY.getOrDefault(role1, 999) : 999;
            int p2 = !role2.isEmpty() ? ROLE_PRIORITY.getOrDefault(role2, 999) : 999;
            return Integer.compare(p1, p2);
        }

        return 0;
    }

    @Inject(method = "render", at = @At("HEAD"))
    private void onRender(CallbackInfo ci) {
        if (client.world != null && !TabPlayerHighlighter.CONFIG.apiUrl().isEmpty()) {
            if (firstRender) {
                firstRender = false;
                fetchRoles();
            }
        } else {
            firstRender = true;
        }
    }

    @Unique
    private void fetchRoles() {
        String apiUrl = TabPlayerHighlighter.CONFIG.apiUrl();
        System.out.println("[TabHighlighter] Fetching roles from: " + apiUrl);

        CompletableFuture.runAsync(() -> {
            try {
                HttpClient httpClient = HttpClient.newHttpClient();
                HttpRequest request = HttpRequest.newBuilder()
                        .uri(URI.create(apiUrl))
                        .build();

                HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
                System.out.println("[TabHighlighter] API response: " + response.statusCode() + " " + response.body());

                if (response.statusCode() == 200) {
                    Type responseType = new TypeToken<Map<String, String>>(){}.getType();
                    Map<String, String> players = new Gson().fromJson(response.body(), responseType);

                    if (players != null) {
                        synchronized (ROLES) {
                            ROLES.clear();
                            players.forEach((uuid, discordRoleId) -> {
                                try {
                                    UUID playerUUID = parseUUID(uuid);
                                    if (playerUUID != null) {
                                        ROLES.put(playerUUID, discordRoleId);
                                        System.out.println("[TabHighlighter] Added role: " + playerUUID + " -> " + discordRoleId);
                                    } else {
                                        System.out.println("[TabHighlighter] Invalid UUID format: " + uuid);
                                    }
                                } catch (IllegalArgumentException e) {
                                    System.out.println("[TabHighlighter] Error processing UUID: " + uuid + " - " + e.getMessage());
                                }
                            });
                        }
                    }
                }
            } catch (Exception e) {
                System.out.println("[TabHighlighter] API Error: " + e.getMessage());
                e.printStackTrace();
            }
        });
    }
}