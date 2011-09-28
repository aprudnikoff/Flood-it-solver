import java.awt.AWTException;
import java.awt.Robot;
import java.io.IOException;

import com.sun.jna.Native;
import com.sun.jna.Pointer;
import com.sun.jna.platform.win32.User32;
import com.sun.jna.platform.win32.WinDef;
import com.sun.jna.platform.win32.WinDef.HWND;
import com.sun.jna.platform.win32.WinUser;
import com.sun.jna.platform.win32.WinUser.WINDOWINFO;
import com.sun.jna.win32.StdCallLibrary;

public class Rr {

	public static void main(String[] args) throws IOException {
		final String s = "Google - Google Chrome";
		final String c = "Chrome_WidgetWin_0";
		final User32 user32 = User32.INSTANCE;
		final int length = 512;
		char[] windowText = new char[length];
		char[] className = new char[length];

		WinDef.HWND hwnd = user32.FindWindow(c, null);
		user32.GetClassName(hwnd, className, length);
		int i = user32.GetWindowText(hwnd, windowText, length);
		System.out.println(hwnd);
		System.out.println(Native.toString(windowText));
		System.out.println(Native.toString(className));
		System.out.println("i = " + i);
		
		user32.EnumChildWindows(hwnd, new WinUser.WNDENUMPROC() {
			public boolean callback(WinDef.HWND hwnd, Pointer data) {
		        char[] windowText = new char[512];
		        byte[] className = new byte[512];
		        user32.GetWindowText(hwnd, windowText, 512);
//		        user32.GetClassName(hWnd, className, 512);
		        String wText = Native.toString(windowText);
//		        String cName = Native.toString(className);
		        wText = (wText.isEmpty()) ? "" : "; text: " + wText;
//		        cName = (cName.isEmpty()) ? "" : "; class: " + cName;
		        System.out.println("Found window " + hwnd + wText /*+ cName*/);
		        return true;
			}
		}, null);
	}
}
