package com.doudou.cutecore.util;

import com.doudou.cutecore.blocks.BaseCake;
import com.doudou.cutecore.blocks.CuteCoreBlock;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraftforge.registries.RegistryObject;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Supplier;

public class CakeBuilder {

    public static final Map<Supplier<BaseCake>, CakeBuilder> BUILDER_BY_CAKE = new HashMap<>();

    private final String cakeName;
    private final boolean customBlockModel;
    private final boolean customItemModel;
    private final boolean big;
    private CakeClazzSupplier<?> cakeClazz;
    private BlockBehaviour.Properties cakeProperties;

    public CakeBuilder(String cakeName, boolean big, boolean customBlockModel, boolean customItemModel) {
        this.cakeName = cakeName;
        this.big = big;
        this.customBlockModel = customBlockModel;
        this.customItemModel = customItemModel;
    }

    public CakeBuilder(String cakeName, boolean big, boolean customBlockModel) {
        this(cakeName, big, customBlockModel,  false);
    }

    public CakeBuilder(String cakeName, boolean big) {
        this(cakeName, big, false);
    }

    public CakeBuilder(String cakeName) {
        this(cakeName, false);
    }

    public <T extends BaseCake> CakeBuilder setCakeClass(CakeClazzSupplier<T> clazz) {
        cakeClazz = clazz;
        return this;
    }

    public <T extends BaseCake> CakeBuilder setBothClasses(CakeClazzSupplier<T> cakeClazz) {
        this.cakeClazz = cakeClazz;
        return this;
    }

    public CakeBuilder setCakeProperties(BlockBehaviour.Properties properties) {
        cakeProperties = properties;
        return this;
    }


    public Supplier<BaseCake> build() {

        if (cakeClazz == null) {
            if (big){
                cakeClazz = BaseCake::new;
            } else {
                cakeClazz = BaseCake::new;
            }
        }

        if (cakeProperties == null) {
            cakeProperties = CuteCoreBlock.cakeProperties();
        }


        Supplier<BaseCake> cake = CuteCoreBlock.registryBlock(cakeName, () -> cakeClazz.get(this));

        BUILDER_BY_CAKE.put(cake, this);
        return cake;
    }

    public String getCakeName() {
        return cakeName;
    }

    public boolean allowsCandles() {
        return false;
    }

    public boolean hasCustomBlockModel() {
        return customBlockModel;
    }

    public boolean hasCustomItemModel() {
        return customItemModel;
    }

    public BlockBehaviour.Properties getCakeProperties() {
        return cakeProperties;
    }

    @FunctionalInterface
    public interface CakeClazzSupplier<T extends BaseCake> {

        T get(CakeBuilder builder);
    }
}
