/*
The API: int read4(char *buf) reads 4 characters at a time from a file.
The return value is the actual number of characters read. For example, it returns 3 if there is only 3 characters left in the file.
By using the read4 API, implement the function int read(char *buf, int n) that reads n characters from the file.

Note:
The read function may be called multiple times.
*/

/* The read4 API is defined in the parent class Reader4.
      int read4(char[] buf); */

/*
Difference with Read N Characters Given Read4
When we call read4, it may return 4 chars. however, the read might only ask for 1 char, so we should keep the other 3 chars for
the next call.
*/

public class Solution extends Reader4 {
    /**
     * @param buf Destination buffer
     * @param n   Maximum number of characters to read
     * @return    The number of characters read
     */
     
    private char[] buffer = new char[4];
    private int offset = 0; // Start position to read in the buffer for next read
    private int readSize = 0; // number of saved char in the buffer
    
    public int read(char[] buf, int n) {
        int readBytes = 0;
        boolean eof = false;
        while (!eof && readBytes < n) {
            if (readSize == 0) {
                readSize = read4(buffer);
                if (readSize < 4) {
                    eof = true;
                }
            }
            
            int bytes = Math.min(n - readBytes, readSize);
            System.arraycopy(buffer, offset, buf, readBytes, bytes);
            readSize -= bytes;
            offset = (offset + bytes) % 4;
            readBytes += bytes;
        }
        return readBytes;
    }
}

// Another solution could be using a queue as the buffer so we don't need to care much about the offset for 
// the buffer(using array), but it will cost more as it need to copy characters into queue first and then copy into buf

public class Solution extends Reader4 {
    /**
     * @param buf Destination buffer
     * @param n   Maximum number of characters to read
     * @return    The number of characters read
     */
     
    private Queue<Character> queue = new LinkedList<Character>();
    
    public int read(char[] buf, int n) {
        char[] buffer = new char[4];
        int readBytes = 0;
        boolean eof = false;
        
        while (!eof && readBytes < n) {
            int readSize = read4(buffer);
            if (readSize < 4) {
                eof = true;
            }
            for (int i = 0; i < readSize; i++) {
                queue.add(buffer[i]);
            }
            
            int bytes = Math.min(n - readBytes, queue.size());
            
            for (int i = 0; i < bytes; i++) {
                buf[readBytes++] = queue.poll();
            }
        }
        return readBytes;
    }
}
