import java.io.IOException;

import px.robot.util.HWNDResponse;

import com.sun.jna.Native;
import com.sun.jna.Pointer;
import com.sun.jna.platform.win32.User32;
import com.sun.jna.platform.win32.WinDef;
import com.sun.jna.platform.win32.WinUser;
import com.sun.jna.platform.win32.WinDef.HWND;
import com.sun.jna.win32.StdCallLibrary;

public class RTest {

	public interface User321 extends StdCallLibrary {
		User321 INSTANCE = (User321) Native.loadLibrary("user32", User321.class);
		boolean ShowWindow(HWND hwnd, int nCmdShow);
		boolean SetForegroundWindow(HWND hwnd);
	}

	public static void main(String[] args) throws IOException {
		final User32 user32 = User32.INSTANCE;
		final User321 lib = (User321) Native.loadLibrary("user32", User321.class);
		final String windowClassName = "Chrome_WidgetWin_0";
		final String controlClassName = "Chrome_RenderWidgetHostHWND";
		final String tabName = "Игры Google+";

		WinDef.RECT controlRect = new WinDef.RECT();

		final HWNDResponse windowHwnd = new HWNDResponse();
		final HWNDResponse controlHwnd = new HWNDResponse();

		user32.EnumWindows(new WinUser.WNDENUMPROC() {
			public boolean callback(HWND hwnd, Pointer data) {
				char[] className = new char[512];
				user32.GetClassName(hwnd, className, 512);
				String cName = Native.toString(className);
				if (cName.equals(windowClassName)) {
					final HWND parentHwnd = hwnd;

					user32.EnumChildWindows(hwnd, new WinUser.WNDENUMPROC() {
						public boolean callback(HWND hwnd, Pointer data) {
							char[] windowName = new char[512];
							user32.GetWindowText(hwnd, windowName, 512);
							String wName = Native.toString(windowName);

							if (wName.equals(tabName)) {
								windowHwnd.setHwnd(parentHwnd);
								return false;
							}
							return true;
						}
					}, null);
				}
				return true;
			}
		}, null);

		user32.EnumChildWindows(windowHwnd.getHwnd(),
				new WinUser.WNDENUMPROC() {
					public boolean callback(HWND hwnd, Pointer data) {
						char[] className = new char[512];
						user32.GetClassName(hwnd, className, 512);
						String cName = Native.toString(className);
						if (cName.equals(controlClassName)) {
							controlHwnd.setHwnd(hwnd);
						}
						return true;
					}
				}, null);

		user32.GetWindowRect(controlHwnd.getHwnd(), controlRect);

		System.out.println(windowHwnd.getHwnd());
		System.out.println(controlRect);

		int left = (controlRect.right - controlRect.left - 960) / 2 + 278;
		int top = controlRect.top + 160;
		int width = 336;
		int height = 336;
		lib.ShowWindow(windowHwnd.getHwnd(), 8);
		lib.SetForegroundWindow(windowHwnd.getHwnd());
	}
}
