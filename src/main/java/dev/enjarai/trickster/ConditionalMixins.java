package dev.enjarai.trickster;

import com.google.common.collect.ImmutableMap;
import net.fabricmc.loader.api.FabricLoader;
import org.objectweb.asm.tree.ClassNode;
import org.spongepowered.asm.mixin.extensibility.IMixinConfigPlugin;
import org.spongepowered.asm.mixin.extensibility.IMixinInfo;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.function.Supplier;

// stole this impl from https://github.com/Juuxel/Adorn/blob/bd70a2955640897bc68ff1f4f201fe5e6c10bc32/fabric/src/main/java/juuxel/adorn/AdornMixinPlugin.java
public class ConditionalMixins implements IMixinConfigPlugin {
    private static final Supplier<Boolean> TRUE = () -> true;

    private static final Map<String, Supplier<Boolean>> CONDITIONS = ImmutableMap.of(
            "dev.enjarai.trickster.mixin.client.sodium.WorldSliceMixin", () -> FabricLoader.getInstance().isModLoaded("sodium"),
            "dev.enjarai.trickster.mixin.client.sodium.ChunkRenderContextMixin", () -> FabricLoader.getInstance().isModLoaded("sodium"),
            "dev.enjarai.trickster.mixin.client.sodium.ChunkBuilderMeshingTaskMixin", () -> FabricLoader.getInstance().isModLoaded("sodium"),
            "dev.enjarai.trickster.mixin.client.figura.AvatarManagerMixin", () -> FabricLoader.getInstance().isModLoaded("figura")
    );

    @Override
    public boolean shouldApplyMixin(String targetClassName, String mixinClassName) {
        return CONDITIONS.getOrDefault(mixinClassName, TRUE).get();
    }

    // Boilerplate

    @Override
    public void onLoad(String mixinPackage) {

    }

    @Override
    public String getRefMapperConfig() {
        return null;
    }

    @Override
    public void acceptTargets(Set<String> myTargets, Set<String> otherTargets) {

    }

    @Override
    public List<String> getMixins() {
        return null;
    }

    @Override
    public void preApply(String targetClassName, ClassNode targetClass, String mixinClassName, IMixinInfo mixinInfo) {

    }

    @Override
    public void postApply(String targetClassName, ClassNode targetClass, String mixinClassName, IMixinInfo mixinInfo) {

    }
}
