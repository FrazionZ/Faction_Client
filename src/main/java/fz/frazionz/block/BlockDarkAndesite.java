package fz.frazionz.block;

import net.minecraft.block.Block;
import net.minecraft.block.material.MapColor;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyEnum;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IStringSerializable;
import net.minecraft.util.NonNullList;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class BlockDarkAndesite extends Block
{
	private static String blockName;
	public final static PropertyEnum<BlockDarkAndesite.VariantType> VARIANT = PropertyEnum.<BlockDarkAndesite.VariantType>create("variant", BlockDarkAndesite.VariantType.class);
	
    public BlockDarkAndesite(String name)
    {
        super(Material.ROCK);
        this.blockName = name;
        this.setDefaultState(this.blockState.getBaseState().withProperty(VARIANT, BlockDarkAndesite.VariantType.NORMAL));
        this.setCreativeTab(CreativeTabs.BUILDING_BLOCKS);
    }

    /**
     * Get the MapColor for this Block and the given BlockState
     */
    public MapColor getMapColor(IBlockState state, IBlockAccess p_180659_2_, BlockPos p_180659_3_)
    {
        return MapColor.OBSIDIAN;
    }
    
    public static String getBlockName() {
    	return blockName;
    }
    
    public int damageDropped(IBlockState state)
    {
        return ((BlockDarkAndesite.VariantType)state.getValue(VARIANT)).getMetadata();
    }
    
    public void getSubBlocks(CreativeTabs itemIn, NonNullList<ItemStack> tab)
    {
    	tab.add(new ItemStack(this, 1, BlockDarkAndesite.VariantType.NORMAL.getMetadata()));
        tab.add(new ItemStack(this, 1, BlockDarkAndesite.VariantType.BRICK.getMetadata()));
        tab.add(new ItemStack(this, 1, BlockDarkAndesite.VariantType.BRICKS.getMetadata()));
        tab.add(new ItemStack(this, 1, BlockDarkAndesite.VariantType.CARVED.getMetadata()));
        tab.add(new ItemStack(this, 1, BlockDarkAndesite.VariantType.CHISELED.getMetadata()));
    }

    public ItemStack getItem(World worldIn, BlockPos pos, IBlockState state)
    {
        return new ItemStack(this, 1, ((BlockDarkAndesite.VariantType)state.getValue(VARIANT)).getMetadata());
    }
    
    public IBlockState getStateFromMeta(int meta)
    {
        return this.getDefaultState().withProperty(VARIANT, BlockDarkAndesite.VariantType.byMetadata(meta));
    }
    
    protected BlockStateContainer createBlockState()
    {
        return new BlockStateContainer(this, new IProperty[] {VARIANT});
    }

    
    public int getMetaFromState(IBlockState state)
    {
        return ((BlockDarkAndesite.VariantType)state.getValue(VARIANT)).getMetadata();
    }
    
    public enum VariantType implements IStringSerializable
    { 	
    	
        NORMAL(0, "stone_blackstone_normal", "normal", MapColor.OBSIDIAN),
        BRICK(1, "stone_blackstone_brick", "brick", MapColor.OBSIDIAN),
        BRICKS(2, "stone_blackstone_bricks", "bricks", MapColor.OBSIDIAN),
    	CARVED(3, "stone_blackstone_carved", "carved", MapColor.OBSIDIAN),
    	CHISELED(4, "stone_blackstone_chiseled", "chiseled", MapColor.OBSIDIAN),
    	;

        private static final BlockDarkAndesite.VariantType[] METADATA_LOOKUP = new BlockDarkAndesite.VariantType[values().length];
        private final int metadata;
        private final String name;
        private final String unlocalizedName;
        private final MapColor color;
        

        private VariantType(int metadataIn, String nameIn, MapColor color)
        {
            this(metadataIn, nameIn, nameIn, color);
        }

        private VariantType(int metadataIn, String nameIn, String unlocalizedNameIn, MapColor color)
        {
            this.metadata = metadataIn;
            this.name = nameIn;
            this.unlocalizedName = unlocalizedNameIn;
            this.color = color;
        }

        public int getMetadata()
        {
            return this.metadata;
        }

        public String getUnlocalizedName()
        {
            return this.unlocalizedName;
        }

        public MapColor getColor()
        {
            return this.color;
        }

        public String toString()
        {
            return this.name;
        }

        public static BlockDarkAndesite.VariantType byMetadata(int metadata)
        {
            if (metadata < 0 || metadata >= METADATA_LOOKUP.length)
            {
                metadata = 0;
            }

            return METADATA_LOOKUP[metadata];
        }

        public String getName()
        {
            return this.name;
        }

        static {
            for (BlockDarkAndesite.VariantType blockvariant$type : values())
            {
                METADATA_LOOKUP[blockvariant$type.getMetadata()] = blockvariant$type;
            }
        }
    }
}
