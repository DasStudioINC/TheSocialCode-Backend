-- Users

INSERT INTO USERS (user_handle, profile_picture, user_name, first_name, last_name, description, password, subscription_type, created_at, email, follower_count, post_count, literal_post_count)
VALUES (
        'yurpha',
        'uploads/yurpha/user_profile/PasLaVideo.png',
        'Yurpha Games',
        'Zack',
        'Da Silva',
        'The Host of this project',
        'javaweb',
        'PREMIUM',
        CURRENT_DATE,
        'yurpha.games@gmail.com',
        0,
        1,
        1
       );

INSERT INTO USERS (user_handle, profile_picture, user_name, first_name, last_name, description, password, subscription_type, created_at, email, follower_count, post_count, literal_post_count)
VALUES (
           'alexdev',
           'uploads/alexdev/user_profile/Screenshot 2026-09-30 221857.png',
           'AlexDev',
           'Alex',
           'Dobian',
           'Here to bring ideas to light.',
           'CatPee7',
           'PREMIUM',
           CURRENT_DATE,
           'alexdev.info@gmail.com',
           0,
           1,
           1
       );

INSERT INTO USERS (user_handle, profile_picture, user_name, first_name, last_name, description, password, subscription_type, created_at, email, follower_count, post_count, literal_post_count)
VALUES (
           'emilydesigns',
           'uploads/alexdev/user_profile/Screenshot 2026-09-30 221857.png',
           'EmilyDesigns',
           'Emily',
           'Lang',
           'Just doing my job in creating the best.',
           'EggLOL9',
           'BASIC',
           CURRENT_DATE,
           'emily.lang@gmail.com',
           0,
           1,
           1
       );


INSERT INTO USERS (user_handle, profile_picture, user_name, first_name, last_name, description, password, subscription_type, created_at, email, follower_count, post_count, literal_post_count)
VALUES (
           'ethancodes',
           'uploads/default_profile/Screenshot 2026-09-14 154637.png',
           'EthanCodes',
           'Ethan',
           'Walker',
           'Building projects one line of code at a time.',
           'MooBork',
           'PREMIUM',
           CURRENT_DATE,
           'ethan.walker@gmail.com',
           3,
           1,
           1
       );

INSERT INTO USERS (user_handle, profile_picture, user_name, first_name, last_name, description, password, subscription_type, created_at, email, follower_count, post_count, literal_post_count)
VALUES (
           'lilywrites',
           'uploads/default_profile/Screenshot 2026-09-14 154637.png',
           'LilyWrites',
           'Lily',
           'Morgan',
           'Sharing stories, thoughts, and creative ideas.',
           'HamSpam',
           'BASIC',
           CURRENT_DATE,
           'lily.morgan@gmail.com',
           5,
           1,
           1
       );

INSERT INTO USERS (user_handle, profile_picture, user_name, first_name, last_name, description, password, subscription_type, created_at, email, follower_count, post_count, literal_post_count)
VALUES (
           'maxgaming',
           'uploads/default_profile/Screenshot 2026-09-14 154637.png',
           'MaxGaming',
           'Max',
           'Thompson',
           'Gaming is more than a hobby. It is a lifestyle.',
           'DuckZap',
           'PREMIUM',
           CURRENT_DATE,
           'max.thompson@gmail.com',
           8,
           1,
           1
       );

INSERT INTO USERS (user_handle, profile_picture, user_name, first_name, last_name, description, password, subscription_type, created_at, email, follower_count, post_count, literal_post_count)
VALUES (
           'oliviaart',
           'uploads/default_profile/Screenshot 2026-09-14 154637.png',
           'OliviaArt',
           'Olivia',
           'Bennett',
           'Turning imagination into something beautiful.',
           'PigWig7',
           'BASIC',
           CURRENT_DATE,
           'olivia.bennett@gmail.com',
           2,
           1,
           1
       );

INSERT INTO USERS (user_handle, profile_picture, user_name, first_name, last_name, description, password, subscription_type, created_at, email, follower_count, post_count, literal_post_count)
VALUES (
           'pixelking',
           'uploads/default_profile/Screenshot 2026-09-14 154637.png',
           'PixelKing',
           'Noah',
           'Reed',
           'Creating worlds one pixel at a time.',
           'TacoCat',
           'PREMIUM',
           CURRENT_DATE,
           'noah.reed@gmail.com',
           6,
           1,
           1
       );

INSERT INTO USERS (user_handle, profile_picture, user_name, first_name, last_name, description, password, subscription_type, created_at, email, follower_count, post_count, literal_post_count)
VALUES (
           'sarahcodes',
           'uploads/default_profile/Screenshot 2026-09-14 154637.png',
           'SarahCodes',
           'Sarah',
           'Mitchell',
           'Learning, coding, and making new things.',
           'YeetPot',
           'BASIC',
           CURRENT_DATE,
           'sarah.mitchell@gmail.com',
           4,
           1,
           1
       );

INSERT INTO USERS (user_handle, profile_picture, user_name, first_name, last_name, description, password, subscription_type, created_at, email, follower_count, post_count, literal_post_count)
VALUES (
           'jakecreates',
           'uploads/default_profile/Screenshot 2026-09-14 154637.png',
           'JakeCreates',
           'Jake',
           'Parker',
           'Always experimenting with new creative ideas.',
           'BoopDog',
           'BASIC',
           CURRENT_DATE,
           'jake.parker@gmail.com',
           1,
           1,
           1
       );



-- Post

-- Posts

INSERT INTO post (userhandle, post_number, post_path, interaction_path, search_title, like_count, view_count, post_name, description, posted_at)
VALUES (
           'yurpha',
           1,
           'uploads/yurpha/post#1/polytunes-heroes-never-die-617232.mp3',
           'uploads/yurpha/post#1/interaction.txt',
           '2026-11-02T00:00yurpha1a1b2c',
           245,
           1520,
           'Welcome to The Social Code!',
           'Welcome everyone! This is the beginning of The Social Code. A place where developers, creators and technology enthusiasts can connect and share their ideas.',
           DATE '2026-11-02'
       );

INSERT INTO post (userhandle, post_number, post_path, interaction_path, search_title, like_count, view_count, post_name, description, posted_at)
VALUES (
           'alexdev',
           1,
           'uploads/alexdev/post#1/Screenshot 2026-10-20 153324.png',
           'uploads/alexdev/post#1/interaction.txt',
           '2026-11-02T00:00alexdev1b2c3d',
           87,
           432,
           'My First Java Project',
           'Just finished building my first major Java application! Learned so much about object-oriented programming, classes and inheritance along the way.',
           DATE '2026-11-02'
       );

INSERT INTO post (userhandle, post_number, post_path, interaction_path, search_title, like_count, view_count, post_name, description, posted_at)
VALUES (
           'sarahcodes',
           1,
           'uploads/sarahcodes/post#1/script.py',
           'uploads/sarahcodes/post#1/interaction.txt',
           '2026-11-02T00:00sarahcodes1c3d4e',
           192,
           875,
           'Why I Love Spring Boot',
           'Spring Boot makes backend development so much easier. Dependency injection, REST controllers and JPA are some of my favourite features.',
           DATE '2026-11-02'
       );

INSERT INTO post (userhandle, post_number, post_path, interaction_path, search_title, like_count, view_count, post_name, description, posted_at)
VALUES (
           'pixelking',
           1,
           'uploads/pixelking/post#1/script.py',
           'uploads/pixelking/post#1/interaction.txt',
           '2026-11-02T00:00pixelking1d4e5f',
           64,
           389,
           'Working on an Indie Game',
           'Spent the entire weekend working on my new indie game. Finally got the movement system working exactly how I wanted!',
           DATE '2026-11-02'
       );

INSERT INTO post (userhandle, post_number, post_path, interaction_path, search_title, like_count, view_count, post_name, description, posted_at)
VALUES (
           'emilydesigns',
           1,
           'uploads/emilydesigns/post#1/Screenshot 2026-10-30 134228.png',
           'uploads/emilydesigns/post#1/interaction.txt',
           '2026-11-02T00:00emilydesigns1e5f6a',
           356,
           1842,
           'Redesigning My Portfolio',
           'Been experimenting with modern UI design. Sometimes the smallest changes in spacing, typography and colour make the biggest difference.',
           DATE '2026-11-02'
       );

INSERT INTO post (userhandle, post_number, post_path, interaction_path, search_title, like_count, view_count, post_name, description, posted_at)
VALUES (
           'jakecreates',
           1,
           'uploads/jakecreates/post#1/2026-10-20 21-20-59.mkv',
           'uploads/jakecreates/post#1/interaction.txt',
           '2026-11-02T00:00jakecreates1f6a7b',
           128,
           691,
           'The Future of Artificial Intelligence',
           'AI is developing incredibly quickly. I am particularly interested in local language models and how we can run powerful AI systems directly on our own computers.',
           DATE '2026-11-02'
       );

INSERT INTO post (userhandle, post_number, post_path, interaction_path, search_title, like_count, view_count, post_name, description, posted_at)
VALUES (
           'lilywrites',
           1,
           'uploads/lilywrites/post#1/2026-11-02 15-28-49.mkv',
           'uploads/lilywrites/post#1/interaction.txt',
           '2026-11-02T00:00lilywrites1a7b8c',
           43,
           267,
           'A Little Reminder',
           'You do not have to accomplish everything in one day. Take your time, enjoy the process and remember that progress is still progress.',
           DATE '2026-11-02'
       );

INSERT INTO post (userhandle, post_number, post_path, interaction_path, search_title, like_count, view_count, post_name, description, posted_at)
VALUES (
           'maxgaming',
           1,
           'uploads/maxgaming/post#1/Crown of Noise.mp3',
           'uploads/maxgaming/post#1/interaction.txt',
           '2026-11-02T00:00maxgaming1b8c9d',
           521,
           2984,
           'Finally Reached My Goal!',
           'After weeks of practice, I finally reached my competitive gaming goal. All that practice actually paid off!',
           DATE '2026-11-02'
       );

INSERT INTO post (userhandle, post_number, post_path, interaction_path, search_title, like_count, view_count, post_name, description, posted_at)
VALUES (
           'oliviaart',
           1,
           'uploads/oliviaart/post#1/resume.txt',
           'uploads/oliviaart/post#1/interaction.txt',
           '2026-11-02T00:00oliviaart1c9d0e',
           174,
           936,
           'My Latest Digital Artwork',
           'Been practising digital illustration lately. I am trying out different brushes, lighting techniques and shading styles. Really happy with how this turned out!',
           DATE '2026-11-02'
       );

INSERT INTO post (userhandle, post_number, post_path, interaction_path, search_title, like_count, view_count, post_name, description, posted_at)
VALUES (
           'ethancodes',
           1,
           'uploads/ethancodes/post#1/Screenshot 2026-07-03 202806.png',
           'uploads/ethancodes/post#1/interaction.txt',
           '2026-11-02T00:00ethancodes1d0e1f',
           213,
           1105,
           'Learning Something New Every Day',
           'Recently started learning TypeScript after working with JavaScript. Static typing takes some getting used to, but I can already see the benefits!',
           DATE '2026-11-02'
       );


-- Interactions

INSERT INTO interactions (
    user_handle_interacted,
    user_handle_poster,
    content,
    type
)
VALUES (
           'alexdev',
           'sarahcodes',
           '',
           'LIKE'
       );

INSERT INTO interactions (
    user_handle_interacted,
    user_handle_poster,
    content,
    type
)
VALUES (
           'pixelking',
           'emilydesigns',
           '',
           'LIKE'
       );

INSERT INTO interactions (
    user_handle_interacted,
    user_handle_poster,
    content,
    type
)
VALUES (
           'jakecreates',
           'yurpha',
           '',
           'LIKE'
       );

INSERT INTO interactions (
    user_handle_interacted,
    user_handle_poster,
    content,
    type
)
VALUES (
           'lilywrites',
           'maxgaming',
           'Congratulations on reaching your goal!',
           'COMMENT'
       );

INSERT INTO interactions (
    user_handle_interacted,
    user_handle_poster,
    content,
    type
)
VALUES (
           'ethancodes',
           'sarahcodes',
           'Spring Boot is really useful!',
           'COMMENT'
       );

INSERT INTO interactions (
    user_handle_interacted,
    user_handle_poster,
    content,
    type
)
VALUES (
           'oliviaart',
           'pixelking',
           'Love the game concept!',
           'COMMENT'
       );

INSERT INTO interactions (
    user_handle_interacted,
    user_handle_poster,
    content,
    type
)
VALUES (
           'alexdev',
           'sarahcodes',
           '',
           'FOLLOW'
       );

INSERT INTO interactions (
    user_handle_interacted,
    user_handle_poster,
    content,
    type
)
VALUES (
           'sarahcodes',
           'yurpha',
           '',
           'FOLLOW'
       );

INSERT INTO interactions (
    user_handle_interacted,
    user_handle_poster,
    content,
    type
)
VALUES (
           'maxgaming',
           'emilydesigns',
           '',
           'FOLLOW'
       );

INSERT INTO interactions (
    user_handle_interacted,
    user_handle_poster,
    content,
    type
)
VALUES (
           'jakecreates',
           'lilywrites',
           '',
           'FOLLOW'
       );