package soot.toDex.instructions;

import java.util.BitSet;

import org.jf.dexlib.Code.Instruction;
import org.jf.dexlib.Code.Opcode;
import org.jf.dexlib.Code.Format.Instruction21s;

import soot.toDex.Register;

/**
 * The "21s" instruction format: It needs two 16-bit code units, has one register
 * and is used for a 16-bit literal (hence the "s" for "short").<br>
 * <br>
 * It is used by the opcodes "const/16" and "const-wide/16".
 */
public class Insn21s extends AbstractInsn implements OneRegInsn {
	
	private short litB;

	public Insn21s(Opcode opc, Register regA, short litB) {
		super(opc);
		regs.add(regA);
		this.litB = litB;
	}
	
	public Register getRegA() {
		return regs.get(REG_A_IDX);
	}
	
	public short getLitB() {
		return litB;
	}

	@Override
	protected Instruction getRealInsn0() {
		return new Instruction21s(opc, (short) getRegA().getNumber(), getLitB());
	}
	
	@Override
	public BitSet getIncompatibleRegs() {
		BitSet incompatRegs = new BitSet(1);
		if (!getRegA().fitsShort()) {
			incompatRegs.set(REG_A_IDX);
		}
		return incompatRegs;
	}
	
	@Override
	public String toString() {
		return super.toString() + " lit: " + getLitB();
	}
}