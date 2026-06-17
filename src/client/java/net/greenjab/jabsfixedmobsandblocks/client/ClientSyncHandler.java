package net.greenjab.jabsfixedmobsandblocks.client;

import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.greenjab.jabsfixedmobsandblocks.network.*;
import net.greenjab.jabsfixedmobsandblocks.registry.registries.ParticleRegistry;
import net.minecraft.client.Minecraft;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.core.particles.SimpleParticleType;
import net.minecraft.network.chat.Component;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.npc.villager.Villager;
import net.minecraft.world.phys.Vec3;

public class ClientSyncHandler {
    public static void init() {
        ClientPlayNetworking.registerGlobalReceiver(VillagerNeedsPayload.PACKET_ID, ClientSyncHandler::villagerNeed);
    }
    private static void villagerNeed(VillagerNeedsPayload payload, ClientPlayNetworking.Context context) {
        context.client().execute(()-> {
            ClientLevel level = context.client().level;
            if (level != null) {
                Entity entity = level.getEntity((payload.villager()));
                if (entity instanceof Villager villager) {
                    RandomSource random = villager.getRandom();
                    Minecraft.getInstance().player.sendSystemMessage(Component.translatable("entity.jabsfixedmobsandblocks.villager."+payload.need(), villager.getName()));

                    SimpleParticleType particle = switch (payload.need()) {
                        case "hungry","very_hungry" -> ParticleRegistry.VILLAGER_HUNGRY;
                        case "tired","very_tired" -> ParticleRegistry.VILLAGER_TIRED;
                        case "lonely","very_lonely" -> ParticleRegistry.VILLAGER_LONELY;
                        case "lazy","very_lazy" -> ParticleRegistry.VILLAGER_LAZY;
                        case "dark","very_dark" -> ParticleRegistry.VILLAGER_DARK;
                        case "night" -> ParticleRegistry.VILLAGER_NIGHT;
                        default -> null;
                    };
                    if (particle != null) {
                        Vec3 d = Minecraft.getInstance().player.position().subtract(villager.position()).horizontal().normalize();
                        level.addAlwaysVisibleParticle(
                                particle,
                                true,
                                villager.getX() + d.x*0.5 + random.nextDouble() / 5.0 * (random.nextBoolean() ? 1 : -1),
                                villager.getY() + 1 + random.nextDouble() / 2.0,
                                villager.getZ() + d.z*0.5 + random.nextDouble() / 5.0 * (random.nextBoolean() ? 1 : -1),
                                0.0,
                                0.02,
                                0.0
                        );
                    }
                }
            }
        });
    }
}
