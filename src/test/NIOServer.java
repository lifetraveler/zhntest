package test;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.nio.charset.Charset;
import java.util.Iterator;


public class NIOServer {
	public static void main(String[] args) throws Exception {
		ServerSocketChannel ssc = ServerSocketChannel.open();
		ssc.socket().bind(new InetSocketAddress(8080));
		ssc.configureBlocking(false);
		Selector selector = Selector.open();
		ssc.register(selector, SelectionKey.OP_ACCEPT);
		Handler handler = new Handler(1024);
		while (true) {
			if (selector.select(3000) == 0) {
				System.out.println("等待请求超时......");
				continue;
			}
			System.out.println("处理请求......");
			Iterator<SelectionKey> keyIter = selector.selectedKeys().iterator();
			while (keyIter.hasNext()) {
				SelectionKey key = keyIter.next();
//				try {
//					if (key.isAcceptable()) {
//						handler.handlerAccept(key);
//					}
//					if (key.isReadable()) {
//						handler.handleRead(key);
//					}
//				} catch (IOException ex) {
//					keyIter.remove();
//					continue;
//				}
				new Thread(new Handler(key)).run();
				keyIter.remove();

			}

		}

	}

	private static class Handler implements Runnable {
		private int bufferSize = 1024;
		private String localCharset = "UTF-8";
		private SelectionKey key;

		public void run() {
			try{
				if(key.isAcceptable())
				{
					handlerAccept();
				}
				if(key.isReadable()){
					handleRead();
				}
				}
			catch(IOException ex){
				ex.printStackTrace();
				
			}
		}
		
		public Handler() {
		}

		public Handler(int bufferSize) {
			this(bufferSize, null);
		}
		
		public Handler(SelectionKey key){
			this.key=key;
		}

		public Handler(String LocalCharset) {
			this(-1,LocalCharset);

		}

		public Handler(int bufferSize, String localCharset) {
			if (bufferSize > 0) {
				this.bufferSize = bufferSize;
			}
			if (localCharset != null) {
				this.localCharset = localCharset;
			}
		}

		public void handlerAccept() throws IOException {
			SocketChannel sc = ((ServerSocketChannel) key.channel()).accept();
			sc.configureBlocking(false);
			sc.register(key.selector(), SelectionKey.OP_READ, ByteBuffer.allocate(bufferSize));
		}

		public void handleRead() throws IOException {
			SocketChannel sc = (SocketChannel) key.channel();
			ByteBuffer buffer = (ByteBuffer) key.attachment();
			buffer.clear();
			if (sc.read(buffer) == -1) {
				sc.close();
			} else {
				buffer.flip();
				String receivedString = Charset.forName(localCharset).
						newDecoder().decode(buffer).toString();
				String[] requestMessage=receivedString.split("\r\n");
				for(String s:requestMessage){
					System.out.println(s);
					if(s.isEmpty()){
						break;
					}
				}
				
				//控制台打印首行
				String[] firstLine=requestMessage[0].split(" ");
				System.out.println();
				System.out.println("Method:\t"+firstLine[0]);
				System.out.println("url:\t"+firstLine[1]);
				System.out.println("HTTP Version:\t"+firstLine[2]);
				System.out.println();
				
				//返回客户端
				StringBuilder sendString=new StringBuilder();
				sendString.append("HTTP/1.1 OK\r\n");
				sendString.append("Content-Type:text/html;charset="+localCharset+"\r\n");
				sendString.append("\r\n");
				sendString.append("<html><head><title>show text</title></head><body>");
				sendString.append("request text is:</br>");
				for (String s : requestMessage) {
					sendString.append(s+"</br>");
				}
				sendString.append("</body></html>");
				buffer = ByteBuffer.wrap(sendString.toString().getBytes(localCharset));
//				System.out.println("received from client: " + receivedString);
//				String sendString = "received data: " + receivedString;
//				buffer = ByteBuffer.wrap(sendString.getBytes(localCharset));
				sc.write(buffer);
				sc.close();

			}

		}
	}
}
