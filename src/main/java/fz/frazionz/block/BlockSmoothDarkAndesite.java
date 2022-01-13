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

public class BlockSmoothDarkAndesite extends Block
{
	private static String blockName;
	public final static PropertyEnum<BlockSmoothDarkAndesite.VariantType> VARIANT = PropertyEnum.<BlockSmoothDarkAndesite.VariantType>create("variant", BlockSmoothDarkAndesite.VariantType.class);
	
    public BlockSmoothDarkAndesite(String name)
    {
        super(Material.ROCK);
        this.blockName = name;
        this.setDefaultState(this.blockState.getBaseState().withProperty(VARIANT, BlockSmoothDarkAndesite.VariantType.NORMAL));
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
        return ((BlockSmoothDarkAndesite.VariantType)state.getValue(VARIANT)).getMetadata();
    }
    
    public void getSubBlocks(CreativeTabs itemIn, NonNullList<ItemStack> tab)
    {
    	tab.add(new ItemStack(this, 1, BlockSmoothDarkAndesite.VariantType.NORMAL.getMetadata()));
        tab.add(new ItemStack(this, 1, BlockSmoothDarkAndesite.VariantType.BRICK.getMetadata()));
        tab.add(new ItemStack(this, 1, BlockSmoothDarkAndesite.VariantType.BRICKS.getMetadata()));
        tab.add(new ItemStack(this, 1, BlockSmoothDarkAndesite.VariantType.CARVED.getMetadata()));
        tab.add(new ItemStack(this, 1, BlockSmoothDarkAndesite.VariantType.CHISELED.getMetadata()));
    }

    public ItemStack getItem(World worldIn, BlockPos pos, IBlockState state)
    {
        return new ItemStack(this, 1, ((BlockSmoothDarkAndesite.VariantType)state.getValue(VARIANT)).getMetadata());
    }
    
    public IBlockState getStateFromMeta(int meta)
    {
        return this.getDefaultState().withProperty(VARIANT, BlockSmoothDarkAndesite.VariantType.byMetadata(meta));
    }
    
    protected BlockStateContainer createBlockState()
    {
        return new BlockStateContainer(this, new IProperty[] {VARIANT});
    }

    
    public int getMetaFromState(IBlockState state)
    {
        return ((BlockSmoothDarkAndesite.VariantType)state.getValue(VARIANT)).getMetadata();
    }
    
    public enum VariantType implements IStringSerializable
    { 	
    	
        NORMAL(0, "stone_blackstone_smooth_normal", "normal", MapColor.OBSIDIAN),
        BRICK(1, "stone_blackstone_smooth_brick", "brick", MapColor.OBSIDIAN),
        BRICKS(2, "stone_blackstone_smooth_bricks", "bricks", MapColor.OBSIDIAN),
    	CARVED(3, "stone_blackstone_smooth_carved", "carved", MapColor.OBSIDIAN),
    	CHISELED(4, "stone_blackstone_smooth_chiseled", "chiseled", MapColor.OBSIDIAN),
    	;

        private static final BlockSmoothDarkAndesite.VariantType[] METADATA_LOOKUP = new BlockSmoothDarkAndesite.VariantType[values().length];
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

        public static BlockSmoothDarkAndesite.VariantType byMetadata(int metadata)
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
            for (BlockSmoothDarkAndesite.VariantType blockvariant$type : values())
            {
                METADATA_LOOKUP[blockvariant$type.getMetadata()] = blockvariant$type;
            }
        }
    }
}
