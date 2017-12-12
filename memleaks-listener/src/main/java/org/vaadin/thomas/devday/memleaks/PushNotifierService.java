package org.vaadin.thomas.devday.memleaks;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.logging.Logger;

import com.vaadin.shared.Registration;

public class PushNotifierService {

	public interface PushListener {
		public void countUpdated(int newCount);
	}

	private static volatile int counter = 0;
	private static final Object lock = new Object();

	private static final List<PushListener> listeners = new ArrayList<>();
	// private static final Map<PushListener, ?> listeners = new
	// WeakHashMap<>();

	static {

		// when class loads, start self-incrementing.
		final ExecutorService executor = Executors.newSingleThreadExecutor();
		executor.submit(() -> {

			Logger.getAnonymousLogger().info("start ticker");
			try {
				while (true) {

					synchronized (lock) {
						counter++;
					}

					listeners.forEach(r -> r.countUpdated(counter));

					// random, 1-5 seconds
					Thread.sleep(new Random().nextInt(4) * 1000 + 1000);
				}
			} catch (final InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		});
	}

	public static Registration addRegistration(PushListener listener) {
		listeners.add(listener);

		return () -> {
			listeners.remove(listener);
			Logger.getAnonymousLogger().info("removed registration");
		};
	}

}
