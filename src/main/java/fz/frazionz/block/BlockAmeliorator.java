package fz.frazionz.block;

import java.util.Random;

import fz.frazionz.tileentity.TileEntityAmeliorator;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
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
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class BlockAmeliorator extends BlockContainer
{

	public static final AxisAlignedBB FULL_BLOCK = new AxisAlignedBB(0.1D, 0.0D, 0.1D, 0.9D, 0.75D, 0.9D);
    
	private static boolean keepInventory;
    
    public BlockAmeliorator()
    {
        super(Material.IRON);
        this.setCreativeTab(CreativeTabs.DECORATIONS);
    }
	

    public AxisAlignedBB getBoundingBox(IBlockState state, IBlockAccess source, BlockPos pos)
    {
        return FULL_BLOCK;
    }

    public boolean isFullCube(IBlockState state)
    {
        return false;
    }
    
    // Create a new TileEntityAmeliorator //

	public TileEntity createNewTileEntity(World worldIn, int meta)
	{
		return new TileEntityAmeliorator();
	}
	
	// Is Not a Opaque Cube //
	
	public boolean isOpaqueCube(IBlockState state)
    {
        return false;
    }
	
	
	// Render a Model //
	
	public EnumBlockRenderType getRenderType(IBlockState state)
    {
        return EnumBlockRenderType.MODEL;
    }
    
    // Drop un Ameliorator //
    
    public Item getItemDropped(IBlockState state, Random rand, int fortune)
    {
        return Item.getItemFromBlock(Blocks.AMELIORATOR);
    }
    
    public void onBlockPlacedBy(World worldIn, BlockPos pos, IBlockState state, EntityLivingBase placer, ItemStack stack)
    {
        if (stack.hasDisplayName())
        {
            TileEntity tileentity = worldIn.getTileEntity(pos);

        }
    }
    
    
    // Quand le Block est Activé //
    
    public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn, EnumHand hand, EnumFacing heldItem, float side, float hitX, float hitY)
    {
        if (worldIn.isRemote)
        {
            return true;
        }
        else
        {
            TileEntity tileentity = worldIn.getTileEntity(pos);

            if (tileentity instanceof TileEntityAmeliorator)
            {
                playerIn.displayGUIChest((TileEntityAmeliorator)tileentity);
            }

            return true;
        }
    }
    
    // Break block & drop Item //
    
    public void breakBlock(World worldIn, BlockPos pos, IBlockState state)
    {
    	if (!keepInventory)
        {
        TileEntity tileentity = worldIn.getTileEntity(pos);

	        if (tileentity instanceof TileEntityAmeliorator)
	        {
	            InventoryHelper.dropInventoryItems(worldIn, pos, (TileEntityAmeliorator)tileentity);
	        }
        }

        super.breakBlock(worldIn, pos, state);
    }
}
