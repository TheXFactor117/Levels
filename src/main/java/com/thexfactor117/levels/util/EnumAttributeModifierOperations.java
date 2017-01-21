package com.thexfactor117.levels.util;

/**
 * A helper enum for the AttributeModifier operation parameter (last parameter in the constructor).
 * 
 * @author SanAndreasP
 */
public enum EnumAttributeModifierOperations
{
    /**
     * Adds the modifier value as-is to the base value d0.<br>
     * Further operations will use the sum of all modifiers with this operation plus the inital base value d0 as the sum pool d1.<br>
     * <tt>d1 = [foreach modifiervalue : d0 += modifiervalue]</tt>
     */
    ADD_VAL_TO_BASE,
    /**
     * Multiplies the base value d0 with the modifier value, which is acting as a percentage, then adds the result to the sum pool d1.<br>
     * <tt>d1 += [foreach modifiervalue : d0 * modifiervalue]</tt>
     */
    ADD_PERC_VAL_TO_SUM,
    /**
     * Multiplies and rises the sum pool d1 with the sum of 1.0 + modifier value (which is acting as a percentage).<br>
     * <tt>[foreach modifiervalue : (d1 *= 1.0F + modifiervalue)]</tt>
     */
    RISE_SUM_WITH_PERC_VAL
}
