import { TestBed } from '@angular/core/testing';

import { MentorCalendarService } from './mentor-calendar.service';
import { HttpTestingController, HttpClientTestingModule } from '@angular/common/http/testing';
import { ApiURL } from '../utils/ApiURL';
import { MentorCalendar } from '../models/mentor-calendar';
import { ApiResponse } from '../models/api-response';

let service: MentorCalendarService;
let httpMock: HttpTestingController;

describe('MentorCalendarService Testing', () => {
  
  beforeEach(() => {
    TestBed.configureTestingModule({
      providers: [MentorCalendarService],
      imports: [HttpClientTestingModule]
    })

    service =  TestBed.get(MentorCalendarService); 
    httpMock = TestBed.get(HttpTestingController);
  });

  afterEach(() => {
    httpMock.verify();
  });

  it('should be created', () => {
    const service: MentorCalendarService = TestBed.get(MentorCalendarService);
    expect(service).toBeTruthy();
  });
});

describe('MentorCalendarService.saveMentorCalendar()', () => {
  it('Returned Observable should match the right data', () => {
    let calendarMock = new MentorCalendar(
      1,
      2,
      3,
      4,
      '09:00:00',
      '11:00:00',
      'Mon,Tue,Wed'
    );

    let apiResponseMock = new ApiResponse();
    apiResponseMock.status = "200";
    apiResponseMock.message = "Mentor Calendar successfuly added.";
    apiResponseMock.result = null;

    service.saveMentorCalendar(calendarMock).subscribe(
      actual => {
        expect(actual.status).toBe("200");
        expect(actual.message).toBe("Mentor Calendar successfuly added.");
        expect(actual.result).toBe(null);
      }
    );

    const url = ApiURL.MENTOR_CAL_SAVE;
    const req = httpMock.expectOne(url);
    expect(req.request.url).toBe(url);
    expect(req.request.method).toEqual('POST');

    req.flush(apiResponseMock);
  });
});

describe('MentorCalendarService.editMentorCalendar()', () => {
  it('Returned Observable should match the right data', () => {
    let calendarMock = new MentorCalendar(
      1,
      2,
      3,
      4,
      '09:00:00',
      '11:00:00',
      'Mon,Tue,Wed'
    );

    let apiResponseMock = new ApiResponse();
    apiResponseMock.status = "200";
    apiResponseMock.message = "Mentor Calendar successfuly added.";
    apiResponseMock.result = null;

    service.editMentorCalendar(calendarMock).subscribe(
      actual => {
        expect(actual.status).toBe("200");
        expect(actual.message).toBe("Mentor Calendar successfuly added.");
        expect(actual.result).toBe(null);
      }
    );

    const url = ApiURL.MENTOR_CAL_EDIT;
    const req = httpMock.expectOne(url);
    expect(req.request.url).toBe(url);
    expect(req.request.method).toEqual('PUT');

    req.flush(apiResponseMock);
  });
});

describe('MentorCalendarService.deleteMentorCalendar()', () => {
  it('Returned Observable should match the right data', () => {
    const url = ApiURL.MENTOR_CAL_DELETE + "/1";

    let apiResponseMock = new ApiResponse();
    apiResponseMock.status = "200";
    apiResponseMock.message = "Mentor Calendar successfuly deleted.";
    apiResponseMock.result = null;

    service.deleteMentorCalendar(1).subscribe(
      actual => {
        expect(actual.status).toBe("200");
        expect(actual.message).toBe("Mentor Calendar successfuly deleted.");
        expect(actual.result).toBe(null);
      }
    );

    const req = httpMock.expectOne(url);
    expect(req.request.url).toBe(url);
    expect(req.request.method).toEqual('DELETE');
    req.flush(apiResponseMock);
  });
});

describe('MentorCalendarService.getMentorCalendars()', () => {
  it('Returned Observable should match the right data', () => {
  
    let cal1 = new MentorCalendar(1,2,3,4,'09:00:00','11:00:00','Mon,Tue,Wed');
    let cal2 = new MentorCalendar(11,12,13,14,'07:00:00','08:00:00','Sat,Sun');
    let mockCalendars: MentorCalendar[] = new Array();
    mockCalendars.push(cal1, cal2);

    service.getMentorCalendars(1).subscribe(
      actual => {
        expect(actual.length).toBe(3);
        expect(actual[0].id).toBe(cal1.id);
        expect(actual[1].id).toBe(cal2.id);
      }
    );

    const url = `${ApiURL.MENTOR_CAL}?mentorId=1`;
    const req = httpMock.expectOne(url);
    expect(req.request.url).toBe(url);
    expect(req.request.method).toEqual('GET');
    req.flush(mockCalendars);
  });
});
