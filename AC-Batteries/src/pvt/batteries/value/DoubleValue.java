package pvt.batteries.value;

import arjunasdk.enums.ValueType;
import arjunasdk.interfaces.Value;

public class DoubleValue extends NumberValue {

	public DoubleValue(Double number) {
		super(ValueType.DOUBLE, number);
	}

	@Override
	public Value clone() {
		return new DoubleValue(this.getRawObject());
	}

	@SuppressWarnings({ "unchecked", "unused" })
	private double getRawObject() {
		return (Double) this.object();
	}

	@Override
	public double asDouble() {
		return (Double) this.object();
	}
}
