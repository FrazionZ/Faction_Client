package fz.frazionz.block;

import java.util.Random;

import fz.frazionz.tileentity.TileEntityTrophyForge;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.BlockHorizontal;
import net.minecraft.block.material.EnumPushReaction;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyDirection;
import net.minecraft.block.properties.PropertyEnum;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.inventory.InventoryHelper;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumBlockRenderType;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.IStringSerializable;
import net.minecraft.util.Mirror;
import net.minecraft.util.Rotation;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class BlockTrophyForge extends BlockContainer
{
	public static final PropertyDirection FACING = BlockHorizontal.FACING;
	public static final AxisAlignedBB FULL_BLOCK = new AxisAlignedBB(0.0625D, 0.0D, 0.0625D, 0.9375D, 1.0D, 0.9375D);
    public static final PropertyEnum<BlockTrophyForge.EnumTrophyForgeHalf> HALF = PropertyEnum.<BlockTrophyForge.EnumTrophyForgeHalf>create("half", BlockTrophyForge.EnumTrophyForgeHalf.class);
    
	private static boolean keepInventory;
    
    public BlockTrophyForge()
    {
        super(Material.IRON);
        this.setDefaultState(this.blockState.getBaseState().withProperty(FACING, EnumFacing.NORTH).withProperty(HALF, BlockTrophyForge.EnumTrophyForgeHalf.LOWER));
        this.setCreativeTab(CreativeTabs.DECORATIONS);
    }
    
    /**
     * Called after the block is set in the Chunk data, but before the Tile Entity is set
     */
    public void onBlockAdded(World worldIn, BlockPos pos, IBlockState state)
    {
        this.setDefaultFacing(worldIn, pos, state);
    }
    
    private void setDefaultFacing(World worldIn, BlockPos pos, IBlockState state)
    {
        if (!worldIn.isRemote)
        {
            IBlockState iblockstate = worldIn.getBlockState(pos.north());
            IBlockState iblockstate1 = worldIn.getBlockState(pos.south());
            IBlockState iblockstate2 = worldIn.getBlockState(pos.west());
            IBlockState iblockstate3 = worldIn.getBlockState(pos.east());
            EnumFacing enumfacing = (EnumFacing)state.getValue(FACING);

            if (enumfacing == EnumFacing.NORTH && iblockstate.isFullBlock() && !iblockstate1.isFullBlock())
            {
                enumfacing = EnumFacing.SOUTH;
            }
            else if (enumfacing == EnumFacing.SOUTH && iblockstate1.isFullBlock() && !iblockstate.isFullBlock())
            {
                enumfacing = EnumFacing.NORTH;
            }
            else if (enumfacing == EnumFacing.WEST && iblockstate2.isFullBlock() && !iblockstate3.isFullBlock())
            {
                enumfacing = EnumFacing.EAST;
            }
            else if (enumfacing == EnumFacing.EAST && iblockstate3.isFullBlock() && !iblockstate2.isFullBlock())
            {
                enumfacing = EnumFacing.WEST;
            }

            worldIn.setBlockState(pos, state.withProperty(FACING, enumfacing), 2);
        }
    }
    
    public AxisAlignedBB getBoundingBox(IBlockState state, IBlockAccess source, BlockPos pos)
    {
        return FULL_BLOCK;
    }
    
    public boolean isFullCube(IBlockState state)
    {
        return false;
    }
    
	public TileEntity createNewTileEntity(World worldIn, int meta)
	{
		return new TileEntityTrophyForge();
	}
	
	public boolean isOpaqueCube(IBlockState state)
    {
        return false;
    }
	
	public EnumBlockRenderType getRenderType(IBlockState state)
    {
        return EnumBlockRenderType.MODEL;
    }
    
    public EnumPushReaction getMobilityFlag(IBlockState state)
    {
        return EnumPushReaction.IGNORE;
    }
	
    public Item getItemDropped(IBlockState state, Random rand, int fortune)
    {
        return Item.getItemFromBlock(Blocks.TROPHY_FORGE);
    }
    
    public boolean canPlaceBlockAt(World worldIn, BlockPos pos)
    {
        return pos.getY() >= 255 ? false : super.canPlaceBlockAt(worldIn, pos) && super.canPlaceBlockAt(worldIn, pos.up());
    }
    
    public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn, EnumHand hand, EnumFacing heldItem, float side, float hitX, float hitY)
    {
        if (worldIn.isRemote)
        {
            return true;
        }
        else
        {
            BlockPos blockpos = pos.down();
            TileEntity tileentity = null;
            
            if (state.getValue(HALF) == BlockTrophyForge.EnumTrophyForgeHalf.UPPER && worldIn.getBlockState(blockpos).getBlock() == this)
            {
                tileentity = worldIn.getTileEntity(blockpos);
            }
            else if (state.getValue(HALF) == BlockTrophyForge.EnumTrophyForgeHalf.LOWER && worldIn.getBlockState(pos).getBlock() == this)
            {
                tileentity = worldIn.getTileEntity(pos);
            }
            
            if (tileentity instanceof TileEntityTrophyForge)
            {
                playerIn.displayGUIChest((TileEntityTrophyForge)tileentity);
            }

            return true;
        }
    }
    
    public void breakBlock(World worldIn, BlockPos pos, IBlockState state)
    {
    	if (!keepInventory)
        {
    		TileEntity tileentity = worldIn.getTileEntity(pos);

	        if (tileentity instanceof TileEntityTrophyForge)
	        {
	            InventoryHelper.dropInventoryItems(worldIn, pos, (TileEntityTrophyForge)tileentity);
	        }
        }

        super.breakBlock(worldIn, pos, state);
    }
    
    public void onBlockHarvested(World worldIn, BlockPos pos, IBlockState state, EntityPlayer player)
    {
        BlockPos blockpos = pos.down();
        BlockPos blockpos1 = pos.up();

        if (state.getValue(HALF) == BlockTrophyForge.EnumTrophyForgeHalf.UPPER && worldIn.getBlockState(blockpos).getBlock() == this)
        {
            if (player.capabilities.isCreativeMode)
            {
                worldIn.setBlockToAir(pos);
            }
            worldIn.setBlockToAir(blockpos);
        }
        else if (state.getValue(HALF) == BlockTrophyForge.EnumTrophyForgeHalf.LOWER && worldIn.getBlockState(blockpos1).getBlock() == this)
        {
            if (player.capabilities.isCreativeMode)
            {
                worldIn.setBlockToAir(pos);
            }
            worldIn.setBlockToAir(blockpos1);
        }
    }
    
    public static void setState(boolean active, World worldIn, BlockPos pos)
    {
        IBlockState iblockstate = worldIn.getBlockState(pos);
        TileEntity tileentity = worldIn.getTileEntity(pos);
        keepInventory = true;
        
        worldIn.setBlockState(pos, Blocks.TROPHY_FORGE.getDefaultState().withProperty(FACING, iblockstate.getValue(FACING)).withProperty(HALF, iblockstate.getValue(HALF)), 3);

        keepInventory = false;

        if (tileentity != null)
        {
            tileentity.validate();
            worldIn.setTileEntity(pos, tileentity);
        }
    }
    
    /**
     * Called by ItemBlocks just before a block is actually set in the world, to allow for adjustments to the
     * IBlockstate
     */
    public IBlockState onBlockPlaced(World worldIn, BlockPos pos, EnumFacing facing, float hitX, float hitY, float hitZ, int meta, EntityLivingBase placer)
    {
        return this.getDefaultState().withProperty(FACING, placer.getHorizontalFacing().getOpposite());
    }
    
    public IBlockState getActualState(IBlockState state, IBlockAccess worldIn, BlockPos pos)
    {
        if (state.getValue(HALF) == BlockTrophyForge.EnumTrophyForgeHalf.LOWER)
        {
            IBlockState iblockstate = worldIn.getBlockState(pos.up());
        }
        else
        {
            IBlockState iblockstate1 = worldIn.getBlockState(pos.down());
            if (iblockstate1.getBlock() == this)
            {
                state = state.withProperty(FACING, iblockstate1.getValue(FACING));
            }
        }

        return state;
    }

    /**
     * Called by ItemBlocks after a block is set in the world, to allow post-place logic
     */
    public void onBlockPlacedBy(World worldIn, BlockPos pos, IBlockState state, EntityLivingBase placer, ItemStack stack)
    {
    	this.placeTrophyForge(worldIn, pos, placer.getHorizontalFacing().getOpposite());
    }
    
    /**
     * Convert the given metadata into a BlockState for this Block
     */
    public IBlockState getStateFromMeta(int meta)
    {
        EnumFacing enumfacing = EnumFacing.getFront(meta);

        if (enumfacing.getAxis() == EnumFacing.Axis.Y)
        {
            enumfacing = EnumFacing.NORTH;
        }
        
        return (meta & 8) > 0 ? (this.getDefaultState().withProperty(FACING, enumfacing).withProperty(HALF, BlockTrophyForge.EnumTrophyForgeHalf.UPPER)) : (this.getDefaultState().withProperty(FACING, enumfacing).withProperty(HALF, BlockTrophyForge.EnumTrophyForgeHalf.LOWER));
    }

    /**
     * Convert the BlockState into the correct metadata value
     */
    public int getMetaFromState(IBlockState state)
    {
        int i = 0;

        if (state.getValue(HALF) == BlockTrophyForge.EnumTrophyForgeHalf.UPPER)
        {
            i = i | 8;
        }
        else
        {
            i = i | ((EnumFacing)state.getValue(BlockTrophyForge.FACING)).rotateY().getHorizontalIndex();
        }
        
        return i;
    }

    /**
     * Returns the blockstate with the given rotation from the passed blockstate. If inapplicable, returns the passed
     * blockstate.
     */
    public IBlockState withRotation(IBlockState state, Rotation rot)
    {
        return state.getValue(HALF) != BlockTrophyForge.EnumTrophyForgeHalf.LOWER ? state : state.withProperty(FACING, rot.rotate((EnumFacing)state.getValue(FACING)));
    }

    /**
     * Returns the blockstate with the given mirror of the passed blockstate. If inapplicable, returns the passed
     * blockstate.
     */
    public IBlockState withMirror(IBlockState state, Mirror mirrorIn)
    {
        return state.withRotation(mirrorIn.toRotation((EnumFacing)state.getValue(FACING)));
    }

    protected BlockStateContainer createBlockState()
    {
        return new BlockStateContainer(this, new IProperty[] {BlockTrophyForge.HALF, BlockTrophyForge.FACING});
    }
    
    private void placeTrophyForge(World worldIn, BlockPos pos, EnumFacing facing)
    {
        BlockPos blockpos2 = pos.up();
        IBlockState iblockstate = this.getDefaultState().withProperty(BlockTrophyForge.FACING, facing);
        worldIn.setBlockState(pos, iblockstate.withProperty(BlockTrophyForge.HALF, BlockTrophyForge.EnumTrophyForgeHalf.LOWER), 2);
        worldIn.setBlockState(blockpos2, iblockstate.withProperty(BlockTrophyForge.HALF, BlockTrophyForge.EnumTrophyForgeHalf.UPPER), 2);
        worldIn.notifyNeighborsOfStateChange(pos, this, false);
        worldIn.notifyNeighborsOfStateChange(blockpos2, this, false);
    }
    
    protected static boolean isTop(int meta)
    {
        return (meta & 8) != 0;
    }
    
    public static enum EnumTrophyForgeHalf implements IStringSerializable
    {
        UPPER,
        LOWER;

        public String toString()
        {
            return this.getName();
        }

        public String getName()
        {
            return this == UPPER ? "upper" : "lower";
        }
    }
}