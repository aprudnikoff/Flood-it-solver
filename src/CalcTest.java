import java.io.IOException;

import com.sun.jna.Native;
import com.sun.jna.platform.win32.User32;
import com.sun.jna.platform.win32.WinDef;
import com.sun.jna.platform.win32.WinDef.HWND;
import com.sun.jna.win32.StdCallLibrary;

public class CalcTest {
	public interface U32 extends StdCallLibrary {
		U32 INSTANCE = (U32) Native.loadLibrary("user32", U32.class);
		boolean ShowWindow(HWND hwnd, int nCmdShow);
		boolean SetForegroundWindow(HWND hwnd);
	}
	
	public static void main(String[] args) throws IOException {
		final User32 user32 = User32.INSTANCE;
		final U32 u32 = U32.INSTANCE;
		HWND hwnd = user32.FindWindow(null, "Калькулятор");
		System.out.println(hwnd);
		u32.ShowWindow(hwnd, 9);
		u32.SetForegroundWindow(hwnd);
	}
}
