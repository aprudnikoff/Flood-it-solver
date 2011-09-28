package px.robot.util;

import com.sun.jna.platform.win32.WinDef;

public class HWNDResponse {
	WinDef.HWND hwnd;

	public WinDef.HWND getHwnd() {
		return hwnd;
	}

	public void setHwnd(WinDef.HWND hwnd) {
		this.hwnd = hwnd;
	}
}
