package net.minecraft.advancements.critereon;

import net.minecraft.advancements.ICriterionInstance;
import net.minecraft.resources.ResourceLocation;

public class AbstractCriterionInstance implements ICriterionInstance
{
    private final ResourceLocation criterion;

    public AbstractCriterionInstance(ResourceLocation criterionIn)
    {
        this.criterion = criterionIn;
    }

    public ResourceLocation getId()
    {
        return this.criterion;
    }

    public String toString()
    {
        return "AbstractCriterionInstance{criterion=" + this.criterion + '}';
    }
}
