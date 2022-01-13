package net.minecraft.block;

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

public class BlockNetherrack extends Block
{
	public static final PropertyEnum<BlockNetherrack.NetherrackType> VARIANT = PropertyEnum.<BlockNetherrack.NetherrackType>create("variant", BlockNetherrack.NetherrackType.class);
	
    public BlockNetherrack()
    {
        super(Material.ROCK);
        this.setDefaultState(this.blockState.getBaseState().withProperty(VARIANT, BlockNetherrack.NetherrackType.NETHERRACK));
        this.setCreativeTab(CreativeTabs.BUILDING_BLOCKS);
    }

    /**
     * Get the MapColor for this Block and the given BlockState
     */
    public MapColor getMapColor(IBlockState state, IBlockAccess p_180659_2_, BlockPos p_180659_3_)
    {
        return MapColor.NETHERRACK;
    }
    
    public int damageDropped(IBlockState state)
    {
    	BlockNetherrack.NetherrackType blockdirt$dirttype = (BlockNetherrack.NetherrackType)state.getValue(VARIANT);

        if (blockdirt$dirttype == BlockNetherrack.NetherrackType.NETHERRACK)
        {
            blockdirt$dirttype = BlockNetherrack.NetherrackType.NETHERRACK;
        }
        else if (blockdirt$dirttype == BlockNetherrack.NetherrackType.NETHERRACK_NYLIUM)
        {
            blockdirt$dirttype = BlockNetherrack.NetherrackType.NETHERRACK_NYLIUM;
        }

        return blockdirt$dirttype.getMetadata();
    }
    
    public void getSubBlocks(CreativeTabs itemIn, NonNullList<ItemStack> tab)
    {
        tab.add(new ItemStack(this, 1, BlockNetherrack.NetherrackType.NETHERRACK.getMetadata()));
        tab.add(new ItemStack(this, 1, BlockNetherrack.NetherrackType.NETHERRACK_NYLIUM.getMetadata()));
    }

    public ItemStack getItem(World worldIn, BlockPos pos, IBlockState state)
    {
        return new ItemStack(this, 1, ((BlockNetherrack.NetherrackType)state.getValue(VARIANT)).getMetadata());
    }
    
    public IBlockState getStateFromMeta(int meta)
    {
        return this.getDefaultState().withProperty(VARIANT, BlockNetherrack.NetherrackType.byMetadata(meta));
    }
    
    protected BlockStateContainer createBlockState()
    {
        return new BlockStateContainer(this, new IProperty[] {VARIANT});
    }

    
    public int getMetaFromState(IBlockState state)
    {
        return ((BlockNetherrack.NetherrackType)state.getValue(VARIANT)).getMetadata();
    }
    
    public static enum NetherrackType implements IStringSerializable
    {
        NETHERRACK(0, "netherrack", "default", MapColor.NETHERRACK),
        NETHERRACK_NYLIUM(1, "netherrack_nylium", "nylium", MapColor.NETHERRACK);

        private static final BlockNetherrack.NetherrackType[] METADATA_LOOKUP = new BlockNetherrack.NetherrackType[values().length];
        private final int metadata;
        private final String name;
        private final String unlocalizedName;
        private final MapColor color;

        private NetherrackType(int metadataIn, String nameIn, MapColor color)
        {
            this(metadataIn, nameIn, nameIn, color);
        }

        private NetherrackType(int metadataIn, String nameIn, String unlocalizedNameIn, MapColor color)
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

        public static BlockNetherrack.NetherrackType byMetadata(int metadata)
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
            for (BlockNetherrack.NetherrackType blockdirt$dirttype : values())
            {
                METADATA_LOOKUP[blockdirt$dirttype.getMetadata()] = blockdirt$dirttype;
            }
        }
    }
}
