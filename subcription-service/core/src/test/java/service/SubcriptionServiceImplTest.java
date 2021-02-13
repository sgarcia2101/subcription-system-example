package service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.junit.Before;
import org.junit.Test;

import domain.Newsletter;
import domain.Subcription;
import exception.SubcriptionNotFoundException;
import repository.SubcriptionNotificationRepository;
import repository.SubcriptionRepository;
import service.impl.SubcriptionServiceImpl;

public class SubcriptionServiceImplTest {

	// Tested class
	private SubcriptionServiceImpl subcriptionServiceImpl;

	// Mock
	private SubcriptionRepository subcriptionRepository;

	// Mock
	private SubcriptionNotificationRepository subcriptionNotificationRepository;

	private static Long EXAMPLE_ID = 1L;

	private Subcription sampleSubcription;

	private Newsletter sampleNewsLetter;

	@Before
	public void setup() {
		subcriptionRepository = mock(SubcriptionRepository.class);
		subcriptionNotificationRepository = mock(SubcriptionNotificationRepository.class);

		subcriptionServiceImpl = new SubcriptionServiceImpl(subcriptionRepository, subcriptionNotificationRepository);

		sampleNewsLetter = Newsletter.builder()
				.id(EXAMPLE_ID)
				.name("Newsletter 1")
				.build();

		sampleSubcription = Subcription.builder()
				.email("example@example.org")
				.birthdate(LocalDate.now())
				.consent(true)
				.newsletter(sampleNewsLetter)
				.build();
	}

	@Test
	public void test_save_ok() {
		// Mock
		when(subcriptionRepository.save(any(Subcription.class))).thenReturn(sampleSubcription);
		doNothing().when(subcriptionNotificationRepository).sendNew(any(Subcription.class));

		// Do action
		Subcription savedSubcription = subcriptionServiceImpl.save(sampleSubcription);

		// Check
		assertEquals(sampleSubcription, savedSubcription);

		verify(subcriptionRepository, times(1)).save(sampleSubcription);
		verify(subcriptionNotificationRepository, times(1)).sendNew(sampleSubcription);
	}

	@Test
	public void test_delete_ok() {
		// Mock
		when(subcriptionRepository.get(EXAMPLE_ID)).thenReturn(Optional.of(sampleSubcription));

		// Do action
		subcriptionServiceImpl.delete(EXAMPLE_ID);

		// Check
		verify(subcriptionRepository, times(1)).delete(EXAMPLE_ID);
	}

	@Test(expected = SubcriptionNotFoundException.class)
	public void test_delete_SubcriptionNotExists() {
		// Mock
		when(subcriptionRepository.get(EXAMPLE_ID)).thenReturn(Optional.empty());

		// Do action
		subcriptionServiceImpl.delete(EXAMPLE_ID);
	}

	@Test
	public void test_get_ok() {
		// Mock
		when(subcriptionRepository.get(EXAMPLE_ID)).thenReturn(Optional.of(sampleSubcription));

		// Do action
		Optional<Subcription> returnedSubcription = subcriptionServiceImpl.get(EXAMPLE_ID);

		// Check
		assertTrue(returnedSubcription.isPresent());
		assertEquals(sampleSubcription, returnedSubcription.get());

		verify(subcriptionRepository, times(1)).get(EXAMPLE_ID);
	}

	@Test
	public void test_getAll_ok() {
		// Mock
		when(subcriptionRepository.getAll()).thenReturn(List.of(sampleSubcription));

		// Do action
		List<Subcription> returnedList = subcriptionServiceImpl.getAll();

		// Check
		assertNotNull(returnedList);
		assertFalse(returnedList.isEmpty());
		assertTrue(returnedList.contains(sampleSubcription));

		verify(subcriptionRepository, times(1)).getAll();
	}

}
