-- Users

INSERT INTO users (user_handle, profile_picture, user_name, first_name, last_name, description, password, subscription_type, created_at, email, follower_count, post_count, literal_post_count)
SELECT 'yurpha', 'uploads/yurpha/user_profile/PasLaVideo.png', 'Administrator', 'Zack', 'Da Silva', 'The host of this database', '32212wwcd', 'PREMIUM', DATE '2026-11-02', 'dasstudio@gmail.com', 0, 1, 1
WHERE NOT EXISTS (SELECT 1 FROM users WHERE user_handle = 'yurpha');

INSERT INTO users (user_handle, profile_picture, user_name, first_name, last_name, description, password, subscription_type, created_at, email, follower_count, post_count, literal_post_count)
SELECT 'alexdev', 'uploads/alexdev/user_profile/Screenshot 2026-09-30 221857.png', 'AlexDev', 'Alex', 'Morgan', 'Software developer passionate about technology.', 'password123', 'BASIC', DATE '2026-11-02', 'alex.morgan@example.com', 0, 1, 1
WHERE NOT EXISTS (SELECT 1 FROM users WHERE user_handle = 'alexdev');

INSERT INTO users (user_handle, profile_picture, user_name, first_name, last_name, description, password, subscription_type, created_at, email, follower_count, post_count, literal_post_count)
SELECT 'sarahcodes', 'uploads/default_profile/Screenshot 2026-09-14 154637.png', 'SarahCodes', 'Sarah', 'Williams', 'Java enthusiast and full-stack developer.', 'password123', 'PREMIUM', DATE '2026-11-02', 'sarah.williams@example.com', 0, 1, 1
WHERE NOT EXISTS (SELECT 1 FROM users WHERE user_handle = 'sarahcodes');

INSERT INTO users (user_handle, profile_picture, user_name, first_name, last_name, description, password, subscription_type, created_at, email, follower_count, post_count, literal_post_count)
SELECT 'pixelking', 'uploads/default_profile/Screenshot 2026-09-14 154637.png', 'PixelKing', 'Daniel', 'Smith', 'Gaming, pixel art and indie game development.', 'password123', 'BASIC', DATE '2026-11-02', 'daniel.smith@example.com', 0, 1, 1
WHERE NOT EXISTS (SELECT 1 FROM users WHERE user_handle = 'pixelking');

INSERT INTO users (user_handle, profile_picture, user_name, first_name, last_name, description, password, subscription_type, created_at, email, follower_count, post_count, literal_post_count)
SELECT 'emilydesigns', 'uploads/emilydesigns/user_profile/Screenshot 2026-10-02 101238.png', 'EmilyDesigns', 'Emily', 'Brown', 'UI and UX designer who loves creating beautiful interfaces.', 'password123', 'PREMIUM', DATE '2026-11-02', 'emily.brown@example.com', 0, 1, 1
WHERE NOT EXISTS (SELECT 1 FROM users WHERE user_handle = 'emilydesigns');

INSERT INTO users (user_handle, profile_picture, user_name, first_name, last_name, description, password, subscription_type, created_at, email, follower_count, post_count, literal_post_count)
SELECT 'noahtech', 'uploads/default_profile/Screenshot 2026-09-14 154637.png', 'NoahTech', 'Noah', 'Wilson', 'Exploring AI, programming and emerging technologies.', 'password123', 'BASIC', DATE '2026-11-02', 'noah.wilson@example.com', 0, 1, 1
WHERE NOT EXISTS (SELECT 1 FROM users WHERE user_handle = 'noahtech');

INSERT INTO users (user_handle, profile_picture, user_name, first_name, last_name, description, password, subscription_type, created_at, email, follower_count, post_count, literal_post_count)
SELECT 'lilywrites', 'uploads/default_profile/Screenshot 2026-09-14 154637.png', 'LilyWrites', 'Lily', 'Taylor', 'Writer, reader and storyteller.', 'password123', 'BASIC', DATE '2026-11-02', 'lily.taylor@example.com', 0, 1, 1
WHERE NOT EXISTS (SELECT 1 FROM users WHERE user_handle = 'lilywrites');

INSERT INTO users (user_handle, profile_picture, user_name, first_name, last_name, description, password, subscription_type, created_at, email, follower_count, post_count, literal_post_count)
SELECT 'maxgaming', 'uploads/default_profile/Screenshot 2026-09-14 154637.png', 'MaxGaming', 'Max', 'Johnson', 'Competitive gamer and game reviewer.', 'password123', 'PREMIUM', DATE '2026-11-02', 'max.johnson@example.com', 0, 1, 1
WHERE NOT EXISTS (SELECT 1 FROM users WHERE user_handle = 'maxgaming');

INSERT INTO users (user_handle, profile_picture, user_name, first_name, last_name, description, password, subscription_type, created_at, email, follower_count, post_count, literal_post_count)
SELECT 'oliviaart', 'uploads/default_profile/Screenshot 2026-09-14 154637.png', 'OliviaArt', 'Olivia', 'Martin', 'Digital artist sharing illustrations and creative projects.', 'password123', 'BASIC', DATE '2026-11-02', 'olivia.martin@example.com', 0, 1, 1
WHERE NOT EXISTS (SELECT 1 FROM users WHERE user_handle = 'oliviaart');

INSERT INTO users (user_handle, profile_picture, user_name, first_name, last_name, description, password, subscription_type, created_at, email, follower_count, post_count, literal_post_count)
SELECT 'ethancodes', 'uploads/default_profile/Screenshot 2026-09-14 154637.png', 'EthanCodes', 'Ethan', 'Anderson', 'Learning new programming languages and building projects.', 'password123', 'PREMIUM', DATE '2026-11-02', 'ethan.anderson@example.com', 0, 1, 1
WHERE NOT EXISTS (SELECT 1 FROM users WHERE user_handle = 'ethancodes');


-- Posts

INSERT INTO post (userhandle, post_number, post_path, interaction_path, search_title, like_count, view_count, post_name, description, posted_at)
SELECT 'yurpha', 1, 'uploads/yurpha/post#1/polytunes-heroes-never-die-617232.mp3', 'uploads/yurpha/post#1/interaction.txt', '2026-11-02T00:00yurpha1a1b2c', 245, 1520, 'Welcome to The Social Code!', 'Welcome everyone! This is the beginning of The Social Code. A place where developers, creators and technology enthusiasts can connect and share their ideas.', DATE '2026-11-02'
WHERE NOT EXISTS (SELECT 1 FROM post WHERE userhandle = 'yurpha' AND post_number = 1);

INSERT INTO post (userhandle, post_number, post_path, interaction_path, search_title, like_count, view_count, post_name, description, posted_at)
SELECT 'alexdev', 1, 'uploads/alexdev/post#1/post', 'uploads/alexdev/post#1/interaction.txt', '2026-11-02T00:00alexdev1b2c3d', 87, 432, 'My First Java Project', 'Just finished building my first major Java application! Learned so much about object-oriented programming, classes and inheritance along the way.', DATE '2026-11-02'
WHERE NOT EXISTS (SELECT 1 FROM post WHERE userhandle = 'alexdev' AND post_number = 1);

INSERT INTO post (userhandle, post_number, post_path, interaction_path, search_title, like_count, view_count, post_name, description, posted_at)
SELECT 'sarahcodes', 1, 'uploads/sarahcodes/post#1/post', 'uploads/sarahcodes/post#1/interaction.txt', '2026-11-02T00:00sarahcodes1c3d4e', 192, 875, 'Why I Love Spring Boot', 'Spring Boot makes backend development so much easier. Dependency injection, REST controllers and JPA are some of my favourite features.', DATE '2026-11-02'
WHERE NOT EXISTS (SELECT 1 FROM post WHERE userhandle = 'sarahcodes' AND post_number = 1);

INSERT INTO post (userhandle, post_number, post_path, interaction_path, search_title, like_count, view_count, post_name, description, posted_at)
SELECT 'pixelking', 1, 'uploads/pixelking/post#1/post', 'uploads/pixelking/post#1/interaction.txt', '2026-11-02T00:00pixelking1d4e5f', 64, 389, 'Working on an Indie Game', 'Spent the entire weekend working on my new indie game. Finally got the movement system working exactly how I wanted!', DATE '2026-11-02'
WHERE NOT EXISTS (SELECT 1 FROM post WHERE userhandle = 'pixelking' AND post_number = 1);

INSERT INTO post (userhandle, post_number, post_path, interaction_path, search_title, like_count, view_count, post_name, description, posted_at)
SELECT 'emilydesigns', 1, 'uploads/emilydesigns/post#1/post', 'uploads/emilydesigns/post#1/interaction.txt', '2026-11-02T00:00emilydesigns1e5f6a', 356, 1842, 'Redesigning My Portfolio', 'Been experimenting with modern UI design. Sometimes the smallest changes in spacing, typography and colour make the biggest difference.', DATE '2026-11-02'
WHERE NOT EXISTS (SELECT 1 FROM post WHERE userhandle = 'emilydesigns' AND post_number = 1);

INSERT INTO post (userhandle, post_number, post_path, interaction_path, search_title, like_count, view_count, post_name, description, posted_at)
SELECT 'noahtech', 1, 'uploads/noahtech/post#1/post', 'uploads/noahtech/post#1/interaction.txt', '2026-11-02T00:00noahtech1f6a7b', 128, 691, 'The Future of Artificial Intelligence', 'AI is developing incredibly quickly. I am particularly interested in local language models and how we can run powerful AI systems directly on our own computers.', DATE '2026-11-02'
WHERE NOT EXISTS (SELECT 1 FROM post WHERE userhandle = 'noahtech' AND post_number = 1);

INSERT INTO post (userhandle, post_number, post_path, interaction_path, search_title, like_count, view_count, post_name, description, posted_at)
SELECT 'lilywrites', 1, 'uploads/lilywrites/post#1/post', 'uploads/lilywrites/post#1/interaction.txt', '2026-11-02T00:00lilywrites1a7b8c', 43, 267, 'A Little Reminder', 'You do not have to accomplish everything in one day. Take your time, enjoy the process and remember that progress is still progress.', DATE '2026-11-02'
WHERE NOT EXISTS (SELECT 1 FROM post WHERE userhandle = 'lilywrites' AND post_number = 1);

INSERT INTO post (userhandle, post_number, post_path, interaction_path, search_title, like_count, view_count, post_name, description, posted_at)
SELECT 'maxgaming', 1, 'uploads/maxgaming/post#1/post', 'uploads/maxgaming/post#1/interaction.txt', '2026-11-02T00:00maxgaming1b8c9d', 521, 2984, 'Finally Reached My Goal!', 'After weeks of practice, I finally reached my competitive gaming goal. All that practice actually paid off!', DATE '2026-11-02'
WHERE NOT EXISTS (SELECT 1 FROM post WHERE userhandle = 'maxgaming' AND post_number = 1);

INSERT INTO post (userhandle, post_number, post_path, interaction_path, search_title, like_count, view_count, post_name, description, posted_at)
SELECT 'oliviaart', 1, 'uploads/oliviaart/post#1/post', 'uploads/oliviaart/post#1/interaction.txt', '2026-11-02T00:00oliviaart1c9d0e', 174, 936, 'My Latest Digital Artwork', 'Been practising digital illustration lately. I am trying out different brushes, lighting techniques and shading styles. Really happy with how this turned out!', DATE '2026-11-02'
WHERE NOT EXISTS (SELECT 1 FROM post WHERE userhandle = 'oliviaart' AND post_number = 1);

INSERT INTO post (userhandle, post_number, post_path, interaction_path, search_title, like_count, view_count, post_name, description, posted_at)
SELECT 'ethancodes', 1, 'uploads/ethancodes/post#1/post', 'uploads/ethancodes/post#1/interaction.txt', '2026-11-02T00:00ethancodes1d0e1f', 213, 1105, 'Learning Something New Every Day', 'Recently started learning TypeScript after working with JavaScript. Static typing takes some getting used to, but I can already see the benefits!', DATE '2026-11-02'
WHERE NOT EXISTS (SELECT 1 FROM post WHERE userhandle = 'ethancodes' AND post_number = 1);


-- Recalculate user post counts

UPDATE users
SET post_count = (
    SELECT COUNT(*)
    FROM post
    WHERE post.userhandle = users.user_handle
    );


INSERT INTO interactions (
    user_handle_interacted,
    user_handle_poster,
    content,
    user_id,
    post_id,
    type
)
SELECT 'alexdev', 'sarahcodes', '',
       (SELECT id FROM users WHERE user_handle = 'alexdev'),
       (SELECT id FROM post WHERE userhandle = 'sarahcodes' AND post_number = 1),
       'LIKE'
WHERE NOT EXISTS (
    SELECT 1 FROM interactions
    WHERE user_handle_interacted = 'alexdev'
      AND post_id = (SELECT id FROM post WHERE userhandle = 'sarahcodes' AND post_number = 1)
      AND type = 'LIKE'
);

INSERT INTO interactions (
    user_handle_interacted, user_handle_poster, content, user_id, post_id, type
)
SELECT 'pixelking', 'emilydesigns', '',
       (SELECT id FROM users WHERE user_handle = 'pixelking'),
       (SELECT id FROM post WHERE userhandle = 'emilydesigns' AND post_number = 1),
       'LIKE'
WHERE NOT EXISTS (
    SELECT 1 FROM interactions
    WHERE user_handle_interacted = 'pixelking'
      AND post_id = (SELECT id FROM post WHERE userhandle = 'emilydesigns' AND post_number = 1)
      AND type = 'LIKE'
);

INSERT INTO interactions (
    user_handle_interacted, user_handle_poster, content, user_id, post_id, type
)
SELECT 'noahtech', 'yurpha', '',
       (SELECT id FROM users WHERE user_handle = 'noahtech'),
       (SELECT id FROM post WHERE userhandle = 'yurpha' AND post_number = 1),
       'LIKE'
WHERE NOT EXISTS (
    SELECT 1 FROM interactions
    WHERE user_handle_interacted = 'noahtech'
      AND post_id = (SELECT id FROM post WHERE userhandle = 'yurpha' AND post_number = 1)
      AND type = 'LIKE'
);

INSERT INTO interactions (
    user_handle_interacted, user_handle_poster, content, user_id, post_id, type
)
SELECT 'lilywrites', 'maxgaming', 'Congratulations on reaching your goal!',
       (SELECT id FROM users WHERE user_handle = 'lilywrites'),
       (SELECT id FROM post WHERE userhandle = 'maxgaming' AND post_number = 1),
       'COMMENT'
WHERE NOT EXISTS (
    SELECT 1 FROM interactions
    WHERE user_handle_interacted = 'lilywrites'
      AND post_id = (SELECT id FROM post WHERE userhandle = 'maxgaming' AND post_number = 1)
      AND content = 'Congratulations on reaching your goal!'
      AND type = 'COMMENT'
);

INSERT INTO interactions (
    user_handle_interacted, user_handle_poster, content, user_id, post_id, type
)
SELECT 'ethancodes', 'sarahcodes', 'Spring Boot is really useful!',
       (SELECT id FROM users WHERE user_handle = 'ethancodes'),
       (SELECT id FROM post WHERE userhandle = 'sarahcodes' AND post_number = 1),
       'COMMENT'
WHERE NOT EXISTS (
    SELECT 1 FROM interactions
    WHERE user_handle_interacted = 'ethancodes'
      AND post_id = (SELECT id FROM post WHERE userhandle = 'sarahcodes' AND post_number = 1)
      AND content = 'Spring Boot is really useful!'
      AND type = 'COMMENT'
);

INSERT INTO interactions (
    user_handle_interacted, user_handle_poster, content, user_id, post_id, type
)
SELECT 'oliviaart', 'pixelking', 'Love the game concept!',
       (SELECT id FROM users WHERE user_handle = 'oliviaart'),
       (SELECT id FROM post WHERE userhandle = 'pixelking' AND post_number = 1),
       'COMMENT'
WHERE NOT EXISTS (
    SELECT 1 FROM interactions
    WHERE user_handle_interacted = 'oliviaart'
      AND post_id = (SELECT id FROM post WHERE userhandle = 'pixelking' AND post_number = 1)
      AND content = 'Love the game concept!'
      AND type = 'COMMENT'
);

INSERT INTO interactions (
    user_handle_interacted, user_handle_poster, content, user_id, post_id, type
)
SELECT 'alexdev', 'sarahcodes', '',
       (SELECT id FROM users WHERE user_handle = 'sarahcodes'),
       NULL,
       'FOLLOW'
WHERE NOT EXISTS (
    SELECT 1 FROM interactions
    WHERE user_handle_interacted = 'alexdev'
      AND user_id = (SELECT id FROM users WHERE user_handle = 'sarahcodes')
      AND type = 'FOLLOW'
);

INSERT INTO interactions (
    user_handle_interacted, user_handle_poster, content, user_id, post_id, type
)
SELECT 'sarahcodes', 'yurpha', '',
       (SELECT id FROM users WHERE user_handle = 'yurpha'),
       NULL,
       'FOLLOW'
WHERE NOT EXISTS (
    SELECT 1 FROM interactions
    WHERE user_handle_interacted = 'sarahcodes'
      AND user_id = (SELECT id FROM users WHERE user_handle = 'yurpha')
      AND type = 'FOLLOW'
);

INSERT INTO interactions (
    user_handle_interacted, user_handle_poster, content, user_id, post_id, type
)
SELECT 'maxgaming', 'emilydesigns', '',
       (SELECT id FROM users WHERE user_handle = 'emilydesigns'),
       NULL,
       'FOLLOW'
WHERE NOT EXISTS (
    SELECT 1 FROM interactions
    WHERE user_handle_interacted = 'maxgaming'
      AND user_id = (SELECT id FROM users WHERE user_handle = 'emilydesigns')
      AND type = 'FOLLOW'
);

INSERT INTO interactions (
    user_handle_interacted, user_handle_poster, content, user_id, post_id, type
)
SELECT 'noahtech', 'lilywrites', '',
       (SELECT id FROM users WHERE user_handle = 'lilywrites'),
       NULL,
       'FOLLOW'
WHERE NOT EXISTS (
    SELECT 1 FROM interactions
    WHERE user_handle_interacted = 'noahtech'
      AND user_id = (SELECT id FROM users WHERE user_handle = 'lilywrites')
      AND type = 'FOLLOW'
);

UPDATE users
SET follower_count = (
    SELECT COUNT(*)
    FROM interactions
    WHERE interactions.user_id = users.id
      AND interactions.type = 'FOLLOW'
    );

UPDATE post
SET like_count = (
    SELECT COUNT(*)
    FROM interactions
    WHERE interactions.post_id = post.id
      AND interactions.type = 'LIKE'
    );