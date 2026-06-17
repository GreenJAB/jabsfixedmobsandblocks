package net.greenjab.jabsfixedmobsandblocks.mixin;

import net.greenjab.jabsfixedmobsandblocks.JabsFixedMobsAndBlocks;
import net.greenjab.jabsfixedmobsandblocks.network.Networking;
import net.minecraft.server.MinecraftServer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(MinecraftServer.class)
public abstract class MinecraftServerMixin {
    @Inject(method = "tickServer", at = @At("RETURN"))
    private void loadWorld(CallbackInfo ci) {
        MinecraftServer SW = (MinecraftServer)(Object) this;
        synchronized (Networking.SERVER_LOCK) {
            JabsFixedMobsAndBlocks.SERVER = SW;
            Networking.SERVER_LOCK.notifyAll();
        }
    }
}
