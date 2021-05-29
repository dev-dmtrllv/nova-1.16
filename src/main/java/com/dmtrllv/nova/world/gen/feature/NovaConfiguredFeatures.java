package com.dmtrllv.nova.world.gen.feature;

import com.dmtrllv.nova.Nova;
import com.dmtrllv.nova.Registry;
import com.dmtrllv.nova.block.NovaBlocks;
import com.google.common.collect.ImmutableList;
import com.dmtrllv.nova.RegistryObject;

import net.minecraft.world.gen.blockstateprovider.SimpleBlockStateProvider;
import net.minecraft.world.gen.feature.BaseTreeFeatureConfig;
import net.minecraft.world.gen.feature.ConfiguredFeature;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.FeatureSpread;
import net.minecraft.world.gen.feature.Features;
import net.minecraft.world.gen.feature.TwoLayerFeature;
import net.minecraft.world.gen.foliageplacer.BlobFoliagePlacer;
import net.minecraft.world.gen.placement.AtSurfaceWithExtraConfig;
import net.minecraft.world.gen.placement.Placement;
import net.minecraft.world.gen.trunkplacer.StraightTrunkPlacer;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.registry.WorldGenRegistries;
import net.minecraft.world.gen.blockplacer.SimpleBlockPlacer;
import net.minecraft.world.gen.feature.BlockClusterFeatureConfig;

public final class NovaConfiguredFeatures
{
	public static final Registry<ConfiguredFeature<?, ?>> REGISTRY = new Registry<>((k, o) ->
	{
		ResourceLocation resID = new ResourceLocation(Nova.MOD_ID, k);
		if (WorldGenRegistries.CONFIGURED_FEATURE.keySet().contains(resID))
			throw new IllegalStateException("Configured Feature ID: \"" + resID.toString() + "\" already exists in the Configured Features registry!");

		net.minecraft.util.registry.Registry.register(WorldGenRegistries.CONFIGURED_FEATURE, resID, o);
	});

	// TREES
	public static final RegistryObject<ConfiguredFeature<BaseTreeFeatureConfig, ?>> WHITE_OAK = REGISTRY.register("white_oak", () -> Feature.TREE.configured((new BaseTreeFeatureConfig.Builder(new SimpleBlockStateProvider(NovaBlocks.WHITE_OAK_LOG.get().defaultBlockState()), new SimpleBlockStateProvider(NovaBlocks.WHITE_OAK_LEAVES.get().defaultBlockState()), new BlobFoliagePlacer(FeatureSpread.fixed(2), FeatureSpread.fixed(0), 3), new StraightTrunkPlacer(4, 2, 0), new TwoLayerFeature(1, 0, 1))).ignoreVines().build()));
	public static final RegistryObject<ConfiguredFeature<BaseTreeFeatureConfig, ?>> WHITE_OAK_BEES = REGISTRY.register("white_oak_bees", () -> Feature.TREE.configured(WHITE_OAK.get().config().withDecorators(ImmutableList.of(Features.Placements.BEEHIVE_005))));

	// TREE PLACEMENT
	public static final RegistryObject<ConfiguredFeature<?, ?>> TREES_WHITE_OAK_FOREST = REGISTRY.register("trees_white_oak", () -> WHITE_OAK_BEES.get().decorated(Features.Placements.HEIGHTMAP_SQUARE).decorated(Placement.COUNT_EXTRA.configured(new AtSurfaceWithExtraConfig(10, 0.1F, 1))));
	public static final RegistryObject<ConfiguredFeature<?, ?>> TREES_WHITE_OAK_PLAINS = REGISTRY.register("white_oak_plains_vegetation", () -> WHITE_OAK_BEES.get().decorated(Features.Placements.HEIGHTMAP_SQUARE).decorated(Placement.COUNT_EXTRA.configured(new AtSurfaceWithExtraConfig(0, 0.05F, 1))));

	// PEBBLES
	public static final RegistryObject<ConfiguredFeature<?, ?>> PATCH_PEBBLES = REGISTRY.register("patch_pebbles", () -> NovaFeatures.PEBBLE_PATCH.get().configured(new BlockClusterFeatureConfig.Builder(new SimpleBlockStateProvider(NovaBlocks.PEBBLE.get().defaultBlockState()), new SimpleBlockPlacer()).tries(64).noProjection().build()).decorated((Features.Placements.ADD_32).decorated(Features.Placements.HEIGHTMAP_SQUARE).count(10)));
}
