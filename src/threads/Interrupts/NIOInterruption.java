package threads.Interrupts;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.nio.ByteBuffer;
import java.nio.channels.AsynchronousCloseException;
import java.nio.channels.ClosedByInterruptException;
import java.nio.channels.SocketChannel;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

/**
 * Created by utsav on 8/2/16.
 */

class NIOBlocked implements Runnable{
    private final SocketChannel socketChannel;

    public NIOBlocked(SocketChannel socketChannel){
        this.socketChannel = socketChannel;
    }

    @Override
    public void run() {
        try {
            System.out.println("waiting for read() in " + this);
            socketChannel.read(ByteBuffer.allocate(1));
        }catch (ClosedByInterruptException e){
            System.out.println("ClosedByInterruptException");
        }catch (AsynchronousCloseException e){
            System.out.println("AsynchronousCloseException");
        }catch (IOException e){
            System.out.println("IOException");
        }
        System.out.println("Exiting NIOBlocked.run() " + this);
    }
}

public class NIOInterruption {

    public static void main(String[] args) throws IOException, InterruptedException{
        ExecutorService executorService = Executors.newCachedThreadPool();
        ServerSocket serverSocket = new ServerSocket(8080);
        InetSocketAddress inetSocketAddress = new InetSocketAddress("localhost", 8080);
        SocketChannel socketChannel1 = SocketChannel.open(inetSocketAddress);
        SocketChannel socketChannel2 = SocketChannel.open(inetSocketAddress);
        Future<?> f = executorService.submit(new NIOBlocked(socketChannel1));
        executorService.execute(new NIOBlocked(socketChannel2));
        executorService.shutdown();
        TimeUnit.SECONDS.sleep(1);

        f.cancel(true);
        TimeUnit.SECONDS.sleep(1);

        socketChannel2.close();
    }

}
