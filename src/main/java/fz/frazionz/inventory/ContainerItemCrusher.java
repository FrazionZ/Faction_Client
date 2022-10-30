package fz.frazionz.inventory;

import fz.frazionz.crafting.ItemCrusherRecipes;
import fz.frazionz.tileentity.impl.TileMachine;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.IContainerListener;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;

public class ContainerItemCrusher extends Container implements TileMachine {
	private static final int[] SLOT_CRAFT = new int[] { 0, 1, 2 };
	private final IInventory tileEntity;
	private int crushingTime;
	private int totalCrushingTime;
	private int isCrushing;

	public ContainerItemCrusher(InventoryPlayer playerInventory, IInventory crusherInventory) {
		this.tileEntity = crusherInventory;
		for (int i = 0; i < 3; i++) {
			this.addSlotToContainer(new Slot(crusherInventory, i, 80 + i * 18, 30));
		}

		for (int i = 0; i < 2; ++i) {
			for (int j = 0; j < 5; ++j) {
				this.addSlotToContainer(new Slot(crusherInventory, 3 + j + i * 5, 62 + j * 18, 64 + i * 18));
			}
		}

		for (int i = 0; i < 3; ++i) {
			for (int j = 0; j < 9; ++j) {
				this.addSlotToContainer(new Slot(playerInventory, j + i * 9 + 9, 44 + j * 18, 118 + i * 18));
			}
		}

		for (int k = 0; k < 9; ++k) {
			this.addSlotToContainer(new Slot(playerInventory, k, 44 + k * 18, 176));
		}
	}

	public void addListener(IContainerListener listener)
	{
		super.addListener(listener);
		listener.sendAllWindowProperties(this, this.tileEntity);
	}

	public void detectAndSendChanges()
	{
		super.detectAndSendChanges();

		for (int i = 0; i < this.listeners.size(); ++i) {
			IContainerListener icontainerlistener = this.listeners.get(i);

			if (this.crushingTime != this.tileEntity.getField(0)) {
				icontainerlistener.sendWindowProperty(this, 0, this.tileEntity.getField(0));
			}

			if (this.totalCrushingTime != this.tileEntity.getField(1)) {
				icontainerlistener.sendWindowProperty(this, 1, this.tileEntity.getField(1));
			}

			if (this.isCrushing != this.tileEntity.getField(2)) {
				icontainerlistener.sendWindowProperty(this, 2, this.tileEntity.getField(2));
			}
		}

		this.crushingTime = this.tileEntity.getField(0);
		this.totalCrushingTime = this.tileEntity.getField(1);
		this.isCrushing = this.tileEntity.getField(2);
	}

	public void updateProgressBar(int id, int data) {
		this.tileEntity.setField(id, data);
	}

	public boolean canInteractWith(EntityPlayer playerIn) {
		return this.tileEntity.isUsableByPlayer(playerIn);
	}

	public ItemStack transferStackInSlot(EntityPlayer playerIn, int index) {
		ItemStack itemstack = ItemStack.EMPTY;
		Slot slot = this.inventorySlots.get(index);

		if (slot != null && slot.getHasStack()) {
			ItemStack itemstack1 = slot.getStack();
			itemstack = itemstack1.copy();

			if (index >= 0 && index < 13) {
				if (!this.mergeItemStack(itemstack1, 13, 49, false)) {
					return ItemStack.EMPTY;
				}
			} else if (!this.mergeItemStack(itemstack1, 0, 3, false)) {
				return ItemStack.EMPTY;
			} else if (index >= 13 && index < 40) {
				if (!this.mergeItemStack(itemstack1, 40, 49, false)) {
					return ItemStack.EMPTY;
				}
			} else if (index >= 40 && index < 49) {
				if (!this.mergeItemStack(itemstack1, 13, 40, false)) {
					return ItemStack.EMPTY;
				}
			}

			else if (!this.mergeItemStack(itemstack1, 13, 49, false)) {
				return ItemStack.EMPTY;
			}

			if (itemstack1.isEmpty()) {
				slot.putStack(ItemStack.EMPTY);
			} else {
				slot.onSlotChanged();
			}

			if (itemstack1.getCount() == itemstack.getCount()) {
				return ItemStack.EMPTY;
			}

			slot.onTake(playerIn, itemstack1);
		}

		return itemstack;
	}

	public void startMachine() {
		this.tileEntity.setField(2, 1);
	}


	public ItemStack[] getRecipeResult(int id) {
		return ItemCrusherRecipes.getResult(this.inventorySlots.get(id).getStack());
	}

	public boolean canCrush() {
		boolean canCrush = false;
		for (int id : SLOT_CRAFT)
			canCrush = canCrush || this.getRecipeResult(id) != null;
		return canCrush;
	}

	public boolean isForging() {
		return this.isCrushing == 1;
	}
	public IInventory getTileEntity() {
		return tileEntity;
	}
}
