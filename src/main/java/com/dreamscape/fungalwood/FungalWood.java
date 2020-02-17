package com.dreamscape.fungalwood;

import net.minecraft.block.*;
import net.minecraft.block.material.Material;
import net.minecraft.block.material.MaterialColor;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

@Mod(FungalWood.MOD_ID)
public class FungalWood
{
    public static final String MOD_ID = "fungal_wood";

    public FungalWood()
    {
        FMLJavaModLoadingContext.get().getModEventBus().addGenericListener(Block.class, this::onBlockRegistry);
        FMLJavaModLoadingContext.get().getModEventBus().addGenericListener(Item.class, this::onItemRegistry);
    }

    public static Block FUNGAL_PLANKS,
                        FUNGAL_STAIRS,
                        FUNGAL_SLAB,
                        FUNGAL_DOOR,
                        FUNGAL_TRAPDOOR,
                        FUNGAL_BUTTON,
                        FUNGAL_PRESSURE_PLATE,
                        FUNGAL_FENCE;

    public void onBlockRegistry(final RegistryEvent.Register<Block> event)
    {
        event.getRegistry().registerAll(
                FUNGAL_PLANKS = new Block(Block.Properties.create(Material.WOOD, MaterialColor.WOOL)).setRegistryName(MOD_ID, "fungal_planks"),
                FUNGAL_STAIRS = new StairsBlock(FUNGAL_PLANKS::getDefaultState, Block.Properties.from(FUNGAL_PLANKS)).setRegistryName(MOD_ID, "fungal_stairs"),
                FUNGAL_SLAB = new SlabBlock(Block.Properties.from(FUNGAL_PLANKS)).setRegistryName(MOD_ID, "fungal_slab"),
                FUNGAL_DOOR = new ModDoorBlock(Block.Properties.from(FUNGAL_PLANKS)).setRegistryName(MOD_ID, "fungal_door"),
                FUNGAL_TRAPDOOR = new ModTrapDoorBlock(Block.Properties.from(FUNGAL_PLANKS)).setRegistryName(MOD_ID, "fungal_trapdoor"),
                FUNGAL_BUTTON = new ModWoodButtonBlock(Block.Properties.from(FUNGAL_PLANKS)).setRegistryName(MOD_ID, "fungal_button"),
                FUNGAL_PRESSURE_PLATE = new ModPressurePlateBlock(PressurePlateBlock.Sensitivity.EVERYTHING, Block.Properties.from(FUNGAL_PLANKS)).setRegistryName(MOD_ID, "fungal_pressure_plate")
        );
    }

    public void onItemRegistry(final RegistryEvent.Register<Item> event)
    {
        event.getRegistry().registerAll(
                makeItemFromBlock(FUNGAL_PLANKS, new Item.Properties().group(ItemGroup.BUILDING_BLOCKS)),
                makeItemFromBlock(FUNGAL_STAIRS, new Item.Properties().group(ItemGroup.BUILDING_BLOCKS)),
                makeItemFromBlock(FUNGAL_SLAB, new Item.Properties().group(ItemGroup.BUILDING_BLOCKS)),
                makeItemFromBlock(FUNGAL_DOOR, new Item.Properties().group(ItemGroup.BUILDING_BLOCKS)),
                makeItemFromBlock(FUNGAL_TRAPDOOR, new Item.Properties().group(ItemGroup.BUILDING_BLOCKS)),
                makeItemFromBlock(FUNGAL_BUTTON, new Item.Properties().group(ItemGroup.BUILDING_BLOCKS)),
                makeItemFromBlock(FUNGAL_PRESSURE_PLATE, new Item.Properties().group(ItemGroup.BUILDING_BLOCKS))
        );
    }

    private Item makeItemFromBlock(Block block, Item.Properties itemProperties)
    {
        return new BlockItem(block, itemProperties).setRegistryName(block.getRegistryName());
    }

    private static class ModDoorBlock extends DoorBlock
    {
        public ModDoorBlock(Block.Properties properties)
        {
            super(properties);
        }
    }

    private static class ModTrapDoorBlock extends TrapDoorBlock
    {
        public ModTrapDoorBlock(Block.Properties properties)
        {
            super(properties);
        }
    }

    private static class ModWoodButtonBlock extends WoodButtonBlock
    {
        public ModWoodButtonBlock(Block.Properties properties)
        {
            super(properties);
        }
    }

    private static class ModPressurePlateBlock extends PressurePlateBlock
    {
        public ModPressurePlateBlock(Sensitivity sensitivity, Block.Properties properties)
        {
            super(sensitivity, properties);
        }
    }
}