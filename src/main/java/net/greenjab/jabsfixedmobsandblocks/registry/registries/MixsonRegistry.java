package net.greenjab.jabsfixedmobsandblocks.registry.registries;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import net.greenjab.jabsfixedmobsandblocks.JabsFixedMobsAndBlocks;
import net.minecraft.resources.Identifier;
import net.ramixin.mixson.Mixson;
import net.ramixin.mixson.enums.ErrorPolicy;
import net.ramixin.mixson.enums.Lifetime;
import net.ramixin.mixson.util.Index;

public class MixsonRegistry {

    public static void registerMixsons() {
        System.out.println("register Mixsons");
    }

    static {
        registerAxeBlockTransformer();

    }

    private static void registerAxeBlockTransformer() {
        Mixson.registerEvent(
                1,
                Lifetime.PERSISTENT,
                ErrorPolicy.LOG,
                JabsFixedMobsAndBlocks.id("add_axe_block_transformers").toString(),
                index -> index.idEquals(new Index(Identifier.fromNamespaceAndPath("minecraft", "block_transformer/axe"))),
                context -> {
                    JsonArray root = context.getFile().getAsJsonArray();
                    if (root == null) return;
                    for (JsonElement element : root) {
                        if (element.toString().contains("copper") && !element.toString().contains("waxed")) {
                            element.getAsJsonObject().addProperty("loot", "jabsfixedmobsandblocks:gameplay/other/scrape");
                            element.getAsJsonObject().addProperty("drop_strategy", "clicked_face");
                        }
                    }

                    if (!root.toString().contains("azalea")) {
                        JsonObject newCase = new JsonObject();
                        JsonObject provider = new JsonObject();
                        provider.addProperty("type", "minecraft:rule_based");
                        JsonArray rules = new JsonArray();
                        rules.add(makeRule("jabsfixedmobsandblocks:azalea_wood", "jabsfixedmobsandblocks:stripped_azalea_wood"));
                        rules.add(makeRule("jabsfixedmobsandblocks:azalea_log", "jabsfixedmobsandblocks:stripped_azalea_log"));
                        provider.add("rules", rules);
                        newCase.add("block_state_provider", provider);
                        newCase.addProperty("item_damage_per_use", 1);
                        newCase.addProperty("sound", "minecraft:item.axe.strip");
                        root.add(newCase);
                    }
                }
        );
    }

    private static JsonObject makeRule(String from, String to){
        JsonObject rule = new JsonObject();

        JsonObject if_true = new JsonObject();
        if_true.addProperty("type", "minecraft:matching_blocks");
        if_true.addProperty("blocks", from);
        rule.add("if_true", if_true);

        JsonObject then = new JsonObject();
        then.addProperty("type", "minecraft:copy_properties");
        JsonObject source = new JsonObject();
        source.addProperty("id", to);
        JsonObject properties = new JsonObject();
        properties.addProperty("axis", "y");
        source.add("properties", properties);
        then.add("source", source);
        rule.add("then", then);

        return rule;
    }
}
