package fz.frazionz.block;

import net.minecraft.block.BlockCrops;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class BlockStrawberries extends BlockCrops
{
    private static final AxisAlignedBB[] STAGE_AABB = new AxisAlignedBB[] {new AxisAlignedBB(0.1D, 0.0D, 0.1D, 0.9D, 0.1875, 0.9D), new AxisAlignedBB(0.1D, 0.1D, 0.0D, 0.9D, 0.375D, 0.9D), new AxisAlignedBB(0.1D, 0.0D, 0.1D, 0.9D, 0.4375D, 0.9D), new AxisAlignedBB(0.1D, 0.0D, 0.1D, 0.9D, 0.625D, 0.9D), new AxisAlignedBB(0.1D, 0.0D, 0.1D, 0.9D, 0.625D, 0.9D), new AxisAlignedBB(0.1D, 0.0D, 0.1D, 0.9D, 0.625D, 0.9D), new AxisAlignedBB(0.1D, 0.0D, 0.1D, 0.9D, 0.625D, 0.9D), new AxisAlignedBB(0.1D, 0.0D, 0.1D, 0.9D, 0.625D, 0.9D)};

    protected Item getSeed()
    {
        return Items.STRAWBERRY;
    }

    protected Item getCrop()
    {
        return Items.STRAWBERRY;
    }

    @Override
    public boolean onBlockActivated(World world, BlockPos pos, IBlockState state, EntityPlayer player, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
        if(!world.isRemote) {
            if (this.isMaxAge(state)) {
                Minecraft.getMinecraft().player.swingArm(hand);
                world.setBlockState(pos, this.withAge(3));
                harvestBlock(world, player, pos, state, null, player.getHeldItemMainhand());
                return true;
            }
        }
        return false;
    }

    /**
     * @deprecated call via {@link IBlockState#getBoundingBox(IBlockAccess,BlockPos)} whenever possible.
     * Implementing/overriding is fine.
     */
    public AxisAlignedBB getBoundingBox(IBlockState state, IBlockAccess source, BlockPos pos)
    {
        return STAGE_AABB[((Integer)state.getValue(this.getAgeProperty())).intValue()];
    }
}
