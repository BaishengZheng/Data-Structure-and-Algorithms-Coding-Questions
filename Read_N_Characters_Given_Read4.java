/*
The API: int read4(char *buf) reads 4 characters at a time from a file and save read characters into buf.
The return value is the actual number of characters read. For example, it returns 3 if there is only 3 characters left in the file.
By using the read4 API, implement the function int read(char *buf, int n) that reads n characters from the file.
Note:
The read function will only be called once for each test case.
*/

/* The read4 API is defined in the parent class Reader4.
      int read4(char[] buf); */

public class Solution extends Reader4 {
    /**
     * @param buf Destination buffer
     * @param n   Maximum number of characters to read
     * @return    The number of characters read
     */
    public int read(char[] buf, int n) {
        char[] buffer = new char[4];
        boolean eof = false;
        int readBytes = 0;
        
        while (!eof && readBytes < n) {
            int rd = read4(buffer);
            if (rd < 4) {
                eof = true;
            }
            
            // Case 1: There are more characters in the file than n, so read4 would still reads 4 characters, but we prabably don't need all of them.
            // Case 2: There are not enough characters in the file to fill up the buffer with 4 characters.
            int bytes = Math.min(n - readBytes, rd);
            System.arraycopy(buffer, 0, buf, readBytes, bytes);
            readBytes += bytes;
        }
        return readBytes;
    }
}

