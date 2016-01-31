
package org.bitbuckets.frc2016.subsystems;

import org.bitbuckets.frc2016.Constants;
import org.bitbuckets.frc2016.RobotMap;

import edu.wpi.first.wpilibj.CANTalon;
import edu.wpi.first.wpilibj.CANTalon.TalonControlMode;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class Drivey extends Subsystem {
	private CANTalon right1;
	private CANTalon right2;
	private CANTalon left1;
	private CANTalon left2;
	// Put methods for controlling this subsystem
	// here. Call these from Commands.

	public Drivey() {
		right1 = new CANTalon(RobotMap.rightMotor1);
		right2 = new CANTalon(RobotMap.rightMotor2);
		left1 = new CANTalon(RobotMap.leftMotor1);
		left2 = new CANTalon(RobotMap.leftMotor2);

		right1.changeControlMode(TalonControlMode.Speed);
		left1.changeControlMode(TalonControlMode.Speed);
		right1.configEncoderCodesPerRev(4 * Constants.DRIVE_ENC_CPR);
		left1.configEncoderCodesPerRev(4 * Constants.DRIVE_ENC_CPR);

		right2.changeControlMode(TalonControlMode.Follower);
		left2.changeControlMode(TalonControlMode.Follower);
		right2.set(RobotMap.rightMotor1);
		left2.set(RobotMap.leftMotor1);
	}

	public void initDefaultCommand() {
		// Set the default command for a subsystem here.
		// setDefaultCommand(new MySpecialCommand());
	}

	public void driveMMS(double speed, double radius) {
		// driveWRPM(speed * Constants.DRIVE_VEL_MMS_TO_WRPM, radius *
		// Constants.DRIVE_RAD_TO_WRPM);
	}

	/**
	 * Drives the motor at a speed to get the desired wheel RPM.
	 * 
	 * Equation: left speed = omega (speed/omega - wheel width/2), right speed =
	 * omega (speed/omega + wheel width/2)
	 * 
	 * 
	 * @param speed
	 *            The tangential velocity of the robot in RPM. Forward is
	 *            positive.
	 * @param omega
	 *            The angular velocity of the robot in RAD per MIN. 0 for
	 *            straight. Anticlockwise is positive.
	 */
	public void driveWRPM(double speed, double omega) {
		speed = speed * Constants.WRPM_TO_RPM;
		if (omega == 0) {
			right1.set(speed);
			left1.set(speed);
		} else {
			left1.set(omega * (speed / omega - Constants.WHEEL_WIDTH_REV / 2));
			right1.set(omega * (speed / omega + Constants.WHEEL_WIDTH_REV / 2));
		}
	}
}
