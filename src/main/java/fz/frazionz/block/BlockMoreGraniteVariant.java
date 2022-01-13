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

public class BlockMoreGraniteVariant extends Block
{
	private static String blockName;
	
	public final static PropertyEnum<BlockMoreGraniteVariant.VariantType> VARIANT = PropertyEnum.<BlockMoreGraniteVariant.VariantType>create("variant", BlockMoreGraniteVariant.VariantType.class);
	
    public BlockMoreGraniteVariant(String name)
    {
        super(Material.ROCK);
        this.blockName = name;
        this.setDefaultState(this.blockState.getBaseState().withProperty(VARIANT, BlockMoreGraniteVariant.VariantType.BRICK));
        this.setCreativeTab(CreativeTabs.BUILDING_BLOCKS);
    }

    /**
     * Get the MapColor for this Block and the given BlockState
     */
    public MapColor getMapColor(IBlockState state, IBlockAccess p_180659_2_, BlockPos p_180659_3_)
    {
        return MapColor.STONE;
    }
    
    public static String getBlockName() {
    	
    	return blockName;
    	
    }
    
    public int damageDropped(IBlockState state)
    {
    	BlockMoreGraniteVariant.VariantType blockvariant$type = (BlockMoreGraniteVariant.VariantType)state.getValue(VARIANT);

        if (blockvariant$type == BlockMoreGraniteVariant.VariantType.BRICK)
        {
        	blockvariant$type = BlockMoreGraniteVariant.VariantType.BRICK;
        }
        else if (blockvariant$type == BlockMoreGraniteVariant.VariantType.BRICKS)
        {
        	blockvariant$type = BlockMoreGraniteVariant.VariantType.BRICKS;
        }
        else if (blockvariant$type == BlockMoreGraniteVariant.VariantType.CARVED)
        {
        	blockvariant$type = BlockMoreGraniteVariant.VariantType.CARVED;
        }
        else if (blockvariant$type == BlockMoreGraniteVariant.VariantType.CHISELED)
        {
        	blockvariant$type = BlockMoreGraniteVariant.VariantType.CHISELED;
        }

        return blockvariant$type.getMetadata();
    }
    
    public void getSubBlocks(CreativeTabs itemIn, NonNullList<ItemStack> tab)
    {
        tab.add(new ItemStack(this, 1, BlockMoreGraniteVariant.VariantType.BRICK.getMetadata()));
        tab.add(new ItemStack(this, 1, BlockMoreGraniteVariant.VariantType.BRICKS.getMetadata()));
        tab.add(new ItemStack(this, 1, BlockMoreGraniteVariant.VariantType.CARVED.getMetadata()));
        tab.add(new ItemStack(this, 1, BlockMoreGraniteVariant.VariantType.CHISELED.getMetadata()));
    }

    public ItemStack getItem(World worldIn, BlockPos pos, IBlockState state)
    {
        return new ItemStack(this, 1, ((BlockMoreGraniteVariant.VariantType)state.getValue(VARIANT)).getMetadata());
    }
    
    public IBlockState getStateFromMeta(int meta)
    {
        return this.getDefaultState().withProperty(VARIANT, BlockMoreGraniteVariant.VariantType.byMetadata(meta));
    }
    
    protected BlockStateContainer createBlockState()
    {
        return new BlockStateContainer(this, new IProperty[] {VARIANT});
    }

    
    public int getMetaFromState(IBlockState state)
    {
        return ((BlockMoreGraniteVariant.VariantType)state.getValue(VARIANT)).getMetadata();
    }
    
    public enum VariantType implements IStringSerializable
    { 	
    	
        BRICK(0, "stone_granite" + "_" + "brick", "brick", MapColor.STONE),
        BRICKS(1, "stone_granite" + "_" + "bricks", "bricks", MapColor.STONE),
    	CARVED(2, "stone_granite" + "_" + "carved", "carved", MapColor.STONE),
    	CHISELED(3, "stone_granite" + "_" + "chiseled", "chiseled", MapColor.STONE),
    	;

        private static final BlockMoreGraniteVariant.VariantType[] METADATA_LOOKUP = new BlockMoreGraniteVariant.VariantType[values().length];
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

        public static BlockMoreGraniteVariant.VariantType byMetadata(int metadata)
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
            for (BlockMoreGraniteVariant.VariantType blockvariant$type : values())
            {
                METADATA_LOOKUP[blockvariant$type.getMetadata()] = blockvariant$type;
            }
        }
    }
}
