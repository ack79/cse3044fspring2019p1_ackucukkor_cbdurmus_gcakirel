# cse3044fspring2019p1_ackucukkor_cbdurmus_gcakirel


Register

URL: /user/register
Method: Post
Parameters: 	username -> String, max 30 character, not null
password -> String, max 16 character, not null
user_bio -> String, max 30 character

Example Response:
{
    "success": true,
    "data": {
        "user_id": "16",
        "username": "mokokoo",
        "password": "123456",
        "user_bio": null,
        "user_img": null,
        "reg_date": "2019-05-10 16:30:15"
    }
}


Login

URL: /user/login
Method: Post
Parameters:	username -> String, max 30 character, not null
		password -> String, max 16 character, not null

Example Response:
{
    "success": true,
    "data": {
        "user_id": "16",
        "username": "mokokoo",
        "password": "123456",
        "user_bio": null,
        "user_img": null,
        "reg_date": "2019-05-10 16:30:15"
    }
}





Add Friend

URL: /user/addFriend
Method: Post
Parameters:	user_id -> Arkadaş olarak ekleyen kullanıcı id'si
		friend_user_id -> Arkadaş olarak eklenen kullanıcı id'si

Example Response:
{
    "success": true,
    "message": "Arkadaş ekleme başarılı."
}



Add Post

URL: /post/addPost
Method: Post
Parameters: 	user_id 
		post_text -> Maksimum 240 karakter

Example Response:
{
    "success": true,
    "data": {
        "post_id": "1",
        "user_id": "1",
        "post_text": "bu kitap çok güzellllllll",
        "created_date": "2019-05-10 18:08:20"
    }
}

Get Timeline

URL: /post/getTimeline
Method: Post
Parameters: user_id

Example Response: 
{
    "success": true,
    "data": {
        "post": [
            {
                "post_id": "12",
                "user_id": "18",
                "post_text": "pır pır eder arılar birbirini kovalar",
                "created_date": "2019-05-10 23:01:57"
            },
            {
                "post_id": "11",
                "user_id": "17",
                "post_text": "polis değil kgt",
                "created_date": "2019-05-10 23:01:40"
            },
            {
                "post_id": "10",
                "user_id": "17",
                "post_text": "lorem ipsum dolor sit",
                "created_date": "2019-05-10 23:01:29"
            },
            {
                "post_id": "9",
                "user_id": "17",
                "post_text": "hellö",
                "created_date": "2019-05-10 23:01:21"
            },
            {
                "post_id": "7",
                "user_id": "1",
                "post_text": "holaaaa",
                "created_date": "2019-05-10 23:01:01"
            },
            {
                "post_id": "1",
                "user_id": "1",
                "post_text": "bu kitap çok güzellllllll",
                "created_date": "2019-05-10 18:08:20"
            }
        ],
        "user": [
            {
                "user_id": "1",
                "username": "mokokoo",
                "password": "123456",
                "user_bio": null,
                "user_img": null,
                "reg_date": "2019-05-10 16:04:29"
            },
            {
                "user_id": "17",
                "username": "ack",
                "password": "adam",
                "user_bio": null,
                "user_img": null,
                "reg_date": "2019-05-10 17:25:00"
            },
            {
                "user_id": "18",
                "username": "can berk",
                "password": "adam",
                "user_bio": null,
                "user_img": null,
                "reg_date": "2019-05-10 17:25:06"
            }
        ]
    }
}


Add Advert

URL: /advert/addAdvert
Method: Post
Parameters:     book_id -> zorunlu
                longitude -> zorunlu
                latitude -> zorunlu
                advert_desc -> zorunlu

Example Response:
{
    "success": true,
    "data": {
        "advert_id": "7",
        "book_id": "17",
        "longitude": "142.2234",
        "latitude": "58.45354345",
        "created_date": "2019-06-14 20:51:59",
        "is_active": "1",
        "advert_desc": "deneme test bir iki üç dört beş altı"
    }
}


Get Adverts

URL: /advert/getAdverts
Method: Post
Parameters:     user_id -> zorunlu
                

Example Response:
{
    "success": true,
    "data": [
        {
            "user": [
                {
                    "user_id": "18",
                    "username": "can berk",
                    "password": "adam",
                    "user_bio": null,
                    "user_img": null,
                    "reg_date": "2019-05-10 17:25:06"
                }
            ],
            "book": {
                "book_id": "7",
                "book_name": "test deneme test",
                "book_author": "ali",
                "book_desc": "test test test test test",
                "created_date": "2019-06-08 13:37:18",
                "user_id": "18",
                "img": "http://ahmetcankucukkor.com/libra/uploads/81e7a59499c5e9aebf90798c6a14bcca.jpeg"
            },
            "advertDetails": [
                {
                    "advert_id": "2",
                    "book_id": "7",
                    "longitude": "142.2234",
                    "latitude": "58.45354345",
                    "created_date": "2019-06-08 16:42:07",
                    "is_active": "1",
                    "advert_desc": "deneme test bir iki üç dört beş altı"
                }
            ]
        },
        {
            "user": [
                {
                    "user_id": "18",
                    "username": "can berk",
                    "password": "adam",
                    "user_bio": null,
                    "user_img": null,
                    "reg_date": "2019-05-10 17:25:06"
                }
            ],
            "book": {
                "book_id": "8",
                "book_name": "kürk mantolu",
                "book_author": "ali",
                "book_desc": "test test test test test",
                "created_date": "2019-06-08 13:37:19",
                "user_id": "18",
                "img": "http://ahmetcankucukkor.com/libra/uploads/c06c914da27818a7ea795609394d5cde.jpeg"
            },
            "advertDetails": [
                {
                    "advert_id": "4",
                    "book_id": "8",
                    "longitude": "142.2234",
                    "latitude": "58.45354345",
                    "created_date": "2019-06-08 16:42:12",
                    "is_active": "1",
                    "advert_desc": "deneme test bir iki üç dört beş altı"
                }
            ]
        },
        {
            "user": [
                {
                    "user_id": "18",
                    "username": "can berk",
                    "password": "adam",
                    "user_bio": null,
                    "user_img": null,
                    "reg_date": "2019-05-10 17:25:06"
                }
            ],
            "book": {
                "book_id": "9",
                "book_name": "kürk mantolu",
                "book_author": "ali",
                "book_desc": "test test test test test",
                "created_date": "2019-06-08 13:37:22",
                "user_id": "18",
                "img": "http://ahmetcankucukkor.com/libra/uploads/df7ddbc826029676ec3ae111539b5dbc.jpeg"
            },
            "advertDetails": [
                {
                    "advert_id": "5",
                    "book_id": "9",
                    "longitude": "142.2234",
                    "latitude": "58.45354345",
                    "created_date": "2019-06-08 16:42:16",
                    "is_active": "1",
                    "advert_desc": "deneme test bir iki üç dört beş altı"
                }
            ]
        }
    ]
}

Remove Advert

URL: /advert/removeAdvert
Method: Post
Parameters:     advert_id -> zorunlu

Example Response:
{
    "success": true,
    "message": "Silme işlemi başarılı."
}


Add Book

URL: /book/addBook
Method: Post
Parameters:     user_id -> zorunlu
                book_name -> zorunlu
                book_author -> zorunlu
                book_desc -> zorunlu
                img -> zorunlu değil ama olsa iyi olur resimli olsun uygulama, ya da burayı moklayabilirsin yine default bir resim atarsın lokale
Example Response:
{
    "success": true,
    "data": {
        "book_id": "10",
        "book_name": "142.2234",
        "book_author": "58.45354345",
        "book_desc": "deneme test bir iki üç dört beş altı",
        "created_date": "2019-06-14 21:01:23",
        "user_id": "17",
        "img": "http://ahmetcankucukkor.com/libra/uploads/<"
    }
}



Edit Book

URL: /book/editBook
Method: Post
Parameters:     book_id -> zorunlu
                book_name -> zorunlu değil
                book_author -> zorunlu değil
                book_desc -> zorunlu değil
                img -> zorunlu değil
Example Response:
{
    "success": true
}


Remove Book

URL: /book/removeBook
Method: Post
Parameters:     book_id -> zorunlu
                
Example Response:
{
    "success": true,
    "message": "Kitap silme başarılı."
}


Get Library

URL: /book/getLibrary
Method: Post
Parameters:     user_id -> zorunlu
                
Example Response:
{
    "success": true,
    "data": [
        {
            "book_id": "6",
            "book_name": "kürk mantolu",
            "book_author": "ali",
            "book_desc": "test test test test test",
            "created_date": "2019-06-08 13:37:16",
            "user_id": "18",
            "img": "http://ahmetcankucukkor.com/libra/uploads/86317033377f112e77d2852b78efbfc2.jpeg"
        },
        {
            "book_id": "7",
            "book_name": "test deneme test",
            "book_author": "ali",
            "book_desc": "test test test test test",
            "created_date": "2019-06-08 13:37:18",
            "user_id": "18",
            "img": "http://ahmetcankucukkor.com/libra/uploads/81e7a59499c5e9aebf90798c6a14bcca.jpeg"
        },
        {
            "book_id": "8",
            "book_name": "kürk mantolu",
            "book_author": "ali",
            "book_desc": "test test test test test",
            "created_date": "2019-06-08 13:37:19",
            "user_id": "18",
            "img": "http://ahmetcankucukkor.com/libra/uploads/c06c914da27818a7ea795609394d5cde.jpeg"
        },
        {
            "book_id": "9",
            "book_name": "kürk mantolu",
            "book_author": "ali",
            "book_desc": "test test test test test",
            "created_date": "2019-06-08 13:37:22",
            "user_id": "18",
            "img": "http://ahmetcankucukkor.com/libra/uploads/df7ddbc826029676ec3ae111539b5dbc.jpeg"
        }
    ]
}


Add Message

URL: /message/addMessage
Method: Post
Parameters:     advert_id -> zorunlu
                sender_user_id -> zorunlu
                message_text -> zorunlu
                
Example Response:
{
    "success": true,
    "message": "Kitap silme başarılı."
}

