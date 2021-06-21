namespace java barrier_server

typedef i16 int

struct User{
    1: required string login;
    2: required string id;
}

struct Barrier{
    1: required int id;
    2: required double longitude;
    3: required double latitude;
}

exception UserUnvailable {
    1: string message
}

service BarrierService {
    bool addUser(1: string login, 2: string id),
    bool openBarrier(1: string id, 2: double longitude, 3: double latitude),
    list<Barrier> getBarrierList(1: string id);
}
