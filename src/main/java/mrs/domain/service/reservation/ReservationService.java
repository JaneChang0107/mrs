package mrs.domain.service.reservation;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import mrs.domain.model.ReservableRoom;
import mrs.domain.model.ReservableRoomId;
import mrs.domain.model.Reservation;
import mrs.domain.model.RoleName;
import mrs.domain.model.User;
import mrs.domain.repository.reservation.ReservationRepository;
import mrs.domain.repository.room.ReservableRoomRepository;

@Service
public class ReservationService {

	@Autowired
	ReservationRepository reservationRepository;

	@Autowired
	ReservableRoomRepository reservableRoomRepository;

	public List<Reservation> findReservations(ReservableRoomId reservableRoomId) {
		return reservationRepository.findByReservableRoom_ReservableRoomIdOrderByStartTimeAsc(reservableRoomId);
	}

	public Reservation reserve(Reservation reservation) {

		ReservableRoomId reservableRoomId = reservation.getReservableRoom().getReservableRoomId();
		Optional<ReservableRoom> reservable = reservableRoomRepository.findById(reservableRoomId);
		
		if (reservable == null) {
			throw new UnavailableReservationException("入力の日付と部屋の組み合わせは予約できません");
		}
		boolean overlap = reservationRepository
				.findByReservableRoom_ReservableRoomIdOrderByStartTimeAsc(reservableRoomId).stream()
				.anyMatch(x -> x.overlap(reservation));
		if (overlap) {
			throw new AlreadyReservedException("入力の時間帯はすでに予約済みです");
		}
		reservationRepository.save(reservation);
		

		return reservation;

	}
	
	public void cancel(Integer reservationId,User requestUser) {
		Optional<Reservation> reservation = reservationRepository.findById(reservationId);
		if(RoleName.ADMIN != requestUser.getRoleName() &&
				!Objects.equals(reservation.get().getUser().getUserId(), requestUser.getUserId())) {
			throw new IllegalStateException("要求されたキャンセルは許可できません");
		}
		reservationRepository.deleteById(reservation.get().getReservationId());
	}
	
	

}
