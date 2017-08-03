(ns cljsatd.example)

#_(def twitter-json-string
  "[{\"created_at\":\"Wed Jul 26 23:11:01 +0000 2017\",\"id\":890348762618634241,\"id_str\":\"890348762618634241\",\"text\":\"Recording an episode of @HF_Science with @3Blue1Brown! Streaming live for SciShow patrons :-) - https:\\/\\/t.co\\/FJEqh6P1vs\",\"truncated\":false,\"entities\":{\"hashtags\":[],\"symbols\":[],\"user_mentions\":[{\"screen_name\":\"HF_Science\",\"name\":\"Holy F'ing Science\",\"id\":819691099270873088,\"id_str\":\"819691099270873088\",\"indices\":[24,35]},{\"screen_name\":\"3Blue1Brown\",\"name\":\"Grant Sanderson\",\"id\":2877269376,\"id_str\":\"2877269376\",\"indices\":[41,53]}],\"urls\":[{\"url\":\"https:\\/\\/t.co\\/FJEqh6P1vs\",\"expanded_url\":\"https:\\/\\/www.patreon.com\\/posts\\/hfs-livestream-13399041\",\"display_url\":\"patreon.com\\/posts\\/hfs-live\\u2026\",\"indices\":[96,119]}]},\"source\":\"\\u003ca href=\\\"http:\\/\\/twitter.com\\\" rel=\\\"nofollow\\\"\\u003eTwitter Web Client\\u003c\\/a\\u003e\",\"in_reply_to_status_id\":null,\"in_reply_to_status_id_str\":null,\"in_reply_to_user_id\":null,\"in_reply_to_user_id_str\":null,\"in_reply_to_screen_name\":null,\"user\":{\"id\":61592079,\"id_str\":\"61592079\",\"name\":\"Hank Green \\ud83d\\udc22\",\"screen_name\":\"hankgreen\",\"location\":\"Missoula, MT\",\"description\":\"I make videos. Co-creator of https:\\/\\/t.co\\/nAdRwz1GoJ, https:\\/\\/t.co\\/AxYpeXxCJc, and https:\\/\\/t.co\\/StIhHnlI51. New Dad! Nerdfighter. Montanan. T-Shirt Size: M\",\"url\":\"https:\\/\\/t.co\\/mmC5uxTjDW\",\"entities\":{\"url\":{\"urls\":[{\"url\":\"https:\\/\\/t.co\\/mmC5uxTjDW\",\"expanded_url\":\"http:\\/\\/www.youtube.com\\/vlogbrothers\",\"display_url\":\"youtube.com\\/vlogbrothers\",\"indices\":[0,23]}]},\"description\":{\"urls\":[{\"url\":\"https:\\/\\/t.co\\/nAdRwz1GoJ\",\"expanded_url\":\"http:\\/\\/Complexly.com\",\"display_url\":\"Complexly.com\",\"indices\":[29,52]},{\"url\":\"https:\\/\\/t.co\\/AxYpeXxCJc\",\"expanded_url\":\"http:\\/\\/DFTBA.com\",\"display_url\":\"DFTBA.com\",\"indices\":[54,77]},{\"url\":\"https:\\/\\/t.co\\/StIhHnlI51\",\"expanded_url\":\"http:\\/\\/VidCon.com\",\"display_url\":\"VidCon.com\",\"indices\":[83,106]}]}},\"protected\":false,\"followers_count\":759228,\"friends_count\":648,\"listed_count\":5461,\"created_at\":\"Thu Jul 30 20:36:34 +0000 2009\",\"favourites_count\":11605,\"utc_offset\":-21600,\"time_zone\":\"Mountain Time (US & Canada)\",\"geo_enabled\":true,\"verified\":true,\"statuses_count\":20989,\"lang\":\"en\",\"contributors_enabled\":false,\"is_translator\":false,\"is_translation_enabled\":false,\"profile_background_color\":\"4DB3C8\",\"profile_background_image_url\":\"http:\\/\\/pbs.twimg.com\\/profile_background_images\\/146466597\\/twitterbg2.jpg\",\"profile_background_image_url_https\":\"https:\\/\\/pbs.twimg.com\\/profile_background_images\\/146466597\\/twitterbg2.jpg\",\"profile_background_tile\":false,\"profile_image_url\":\"http:\\/\\/pbs.twimg.com\\/profile_images\\/668834117685874688\\/nAsJZ3j7_normal.png\",\"profile_image_url_https\":\"https:\\/\\/pbs.twimg.com\\/profile_images\\/668834117685874688\\/nAsJZ3j7_normal.png\",\"profile_banner_url\":\"https:\\/\\/pbs.twimg.com\\/profile_banners\\/61592079\\/1483211332\",\"profile_link_color\":\"E81C4F\",\"profile_sidebar_border_color\":\"858585\",\"profile_sidebar_fill_color\":\"C0DDE0\",\"profile_text_color\":\"000000\",\"profile_use_background_image\":true,\"has_extended_profile\":false,\"default_profile\":false,\"default_profile_image\":false,\"following\":null,\"follow_request_sent\":null,\"notifications\":null,\"translator_type\":\"none\"},\"geo\":null,\"coordinates\":null,\"place\":null,\"contributors\":null,\"is_quote_status\":false,\"retweet_count\":2,\"favorite_count\":51,\"favorited\":false,\"retweeted\":false,\"possibly_sensitive\":false,\"lang\":\"en\"},{\"created_at\":\"Wed Jul 26 22:10:18 +0000 2017\",\"id\":890333484476235777,\"id_str\":\"890333484476235777\",\"text\":\"\\ud83d\\udca5\\ud83d\\udca5\\ud83d\\udca5\\ud83d\\udca5\\ud83d\\udca5\\ud83d\\udca5 \\nhttps:\\/\\/t.co\\/4llPqymZaM https:\\/\\/t.co\\/o203gsoPrm\",\"truncated\":false,\"entities\":{\"hashtags\":[],\"symbols\":[],\"user_mentions\":[],\"urls\":[{\"url\":\"https:\\/\\/t.co\\/4llPqymZaM\",\"expanded_url\":\"http:\\/\\/youtube.com\\/eons\",\"display_url\":\"youtube.com\\/eons\",\"indices\":[8,31]},{\"url\":\"https:\\/\\/t.co\\/o203gsoPrm\",\"expanded_url\":\"https:\\/\\/twitter.com\\/KatGibes\\/status\\/890332823973801984\",\"display_url\":\"twitter.com\\/KatGibes\\/statu\\u2026\",\"indices\":[32,55]}]},\"source\":\"\\u003ca href=\\\"http:\\/\\/twitter.com\\\" rel=\\\"nofollow\\\"\\u003eTwitter Web Client\\u003c\\/a\\u003e\",\"in_reply_to_status_id\":null,\"in_reply_to_status_id_str\":null,\"in_reply_to_user_id\":null,\"in_reply_to_user_id_str\":null,\"in_reply_to_screen_name\":null,\"user\":{\"id\":61592079,\"id_str\":\"61592079\",\"name\":\"Hank Green \\ud83d\\udc22\",\"screen_name\":\"hankgreen\",\"location\":\"Missoula, MT\",\"description\":\"I make videos. Co-creator of https:\\/\\/t.co\\/nAdRwz1GoJ, https:\\/\\/t.co\\/AxYpeXxCJc, and https:\\/\\/t.co\\/StIhHnlI51. New Dad! Nerdfighter. Montanan. T-Shirt Size: M\",\"url\":\"https:\\/\\/t.co\\/mmC5uxTjDW\",\"entities\":{\"url\":{\"urls\":[{\"url\":\"https:\\/\\/t.co\\/mmC5uxTjDW\",\"expanded_url\":\"http:\\/\\/www.youtube.com\\/vlogbrothers\",\"display_url\":\"youtube.com\\/vlogbrothers\",\"indices\":[0,23]}]},\"description\":{\"urls\":[{\"url\":\"https:\\/\\/t.co\\/nAdRwz1GoJ\",\"expanded_url\":\"http:\\/\\/Complexly.com\",\"display_url\":\"Complexly.com\",\"indices\":[29,52]},{\"url\":\"https:\\/\\/t.co\\/AxYpeXxCJc\",\"expanded_url\":\"http:\\/\\/DFTBA.com\",\"display_url\":\"DFTBA.com\",\"indices\":[54,77]},{\"url\":\"https:\\/\\/t.co\\/StIhHnlI51\",\"expanded_url\":\"http:\\/\\/VidCon.com\",\"display_url\":\"VidCon.com\",\"indices\":[83,106]}]}},\"protected\":false,\"followers_count\":759228,\"friends_count\":648,\"listed_count\":5461,\"created_at\":\"Thu Jul 30 20:36:34 +0000 2009\",\"favourites_count\":11605,\"utc_offset\":-21600,\"time_zone\":\"Mountain Time (US & Canada)\",\"geo_enabled\":true,\"verified\":true,\"statuses_count\":20989,\"lang\":\"en\",\"contributors_enabled\":false,\"is_translator\":false,\"is_translation_enabled\":false,\"profile_background_color\":\"4DB3C8\",\"profile_background_image_url\":\"http:\\/\\/pbs.twimg.com\\/profile_background_images\\/146466597\\/twitterbg2.jpg\",\"profile_background_image_url_https\":\"https:\\/\\/pbs.twimg.com\\/profile_background_images\\/146466597\\/twitterbg2.jpg\",\"profile_background_tile\":false,\"profile_image_url\":\"http:\\/\\/pbs.twimg.com\\/profile_images\\/668834117685874688\\/nAsJZ3j7_normal.png\",\"profile_image_url_https\":\"https:\\/\\/pbs.twimg.com\\/profile_images\\/668834117685874688\\/nAsJZ3j7_normal.png\",\"profile_banner_url\":\"https:\\/\\/pbs.twimg.com\\/profile_banners\\/61592079\\/1483211332\",\"profile_link_color\":\"E81C4F\",\"profile_sidebar_border_color\":\"858585\",\"profile_sidebar_fill_color\":\"C0DDE0\",\"profile_text_color\":\"000000\",\"profile_use_background_image\":true,\"has_extended_profile\":false,\"default_profile\":false,\"default_profile_image\":false,\"following\":null,\"follow_request_sent\":null,\"notifications\":null,\"translator_type\":\"none\"},\"geo\":null,\"coordinates\":null,\"place\":null,\"contributors\":null,\"is_quote_status\":true,\"quoted_status_id\":890332823973801984,\"quoted_status_id_str\":\"890332823973801984\",\"quoted_status\":{\"created_at\":\"Wed Jul 26 22:07:41 +0000 2017\",\"id\":890332823973801984,\"id_str\":\"890332823973801984\",\"text\":\"@hankgreen I could watch Hank talk about dinosaurs for... well, eons.\",\"truncated\":false,\"entities\":{\"hashtags\":[],\"symbols\":[],\"user_mentions\":[{\"screen_name\":\"hankgreen\",\"name\":\"Hank Green \\ud83d\\udc22\",\"id\":61592079,\"id_str\":\"61592079\",\"indices\":[0,10]}],\"urls\":[]},\"source\":\"\\u003ca href=\\\"http:\\/\\/twitter.com\\\" rel=\\\"nofollow\\\"\\u003eTwitter Web Client\\u003c\\/a\\u003e\",\"in_reply_to_status_id\":890332379197440001,\"in_reply_to_status_id_str\":\"890332379197440001\",\"in_reply_to_user_id\":61592079,\"in_reply_to_user_id_str\":\"61592079\",\"in_reply_to_screen_name\":\"hankgreen\",\"user\":{\"id\":28763613,\"id_str\":\"28763613\",\"name\":\"Kat Gibes \\ud83c\\udff3\\ufe0f\\u200d\\ud83c\\udf08\",\"screen_name\":\"KatGibes\",\"location\":\"Longmont, CO\",\"description\":\"Artist, trans woman, Star Wars fan\",\"url\":null,\"entities\":{\"description\":{\"urls\":[]}},\"protected\":false,\"followers_count\":74,\"friends_count\":260,\"listed_count\":3,\"created_at\":\"Sat Apr 04 08:27:30 +0000 2009\",\"favourites_count\":7532,\"utc_offset\":-21600,\"time_zone\":\"Mountain Time (US & Canada)\",\"geo_enabled\":false,\"verified\":false,\"statuses_count\":3467,\"lang\":\"en\",\"contributors_enabled\":false,\"is_translator\":false,\"is_translation_enabled\":false,\"profile_background_color\":\"1A1B1F\",\"profile_background_image_url\":\"http:\\/\\/abs.twimg.com\\/images\\/themes\\/theme9\\/bg.gif\",\"profile_background_image_url_https\":\"https:\\/\\/abs.twimg.com\\/images\\/themes\\/theme9\\/bg.gif\",\"profile_background_tile\":false,\"profile_image_url\":\"http:\\/\\/pbs.twimg.com\\/profile_images\\/624012494562942976\\/Homm7uSn_normal.jpg\",\"profile_image_url_https\":\"https:\\/\\/pbs.twimg.com\\/profile_images\\/624012494562942976\\/Homm7uSn_normal.jpg\",\"profile_banner_url\":\"https:\\/\\/pbs.twimg.com\\/profile_banners\\/28763613\\/1437611824\",\"profile_link_color\":\"2FC2EF\",\"profile_sidebar_border_color\":\"000000\",\"profile_sidebar_fill_color\":\"000000\",\"profile_text_color\":\"000000\",\"profile_use_background_image\":true,\"has_extended_profile\":true,\"default_profile\":false,\"default_profile_image\":false,\"following\":null,\"follow_request_sent\":null,\"notifications\":null,\"translator_type\":\"none\"},\"geo\":null,\"coordinates\":null,\"place\":null,\"contributors\":null,\"is_quote_status\":false,\"retweet_count\":1,\"favorite_count\":48,\"favorited\":false,\"retweeted\":false,\"lang\":\"en\"},\"retweet_count\":5,\"favorite_count\":182,\"favorited\":false,\"retweeted\":false,\"possibly_sensitive\":false,\"lang\":\"und\"},{\"created_at\":\"Wed Jul 26 22:05:55 +0000 2017\",\"id\":890332379197440001,\"id_str\":\"890332379197440001\",\"text\":\"We couldn\\u2019t possibly know the answer to this question\\u2026right? WRONG. https:\\/\\/t.co\\/86kIgk3MZe\",\"truncated\":false,\"entities\":{\"hashtags\":[],\"symbols\":[],\"user_mentions\":[],\"urls\":[{\"url\":\"https:\\/\\/t.co\\/86kIgk3MZe\",\"expanded_url\":\"https:\\/\\/www.youtube.com\\/watch?v=vtpi7yUHNyg\",\"display_url\":\"youtube.com\\/watch?v=vtpi7y\\u2026\",\"indices\":[68,91]}]},\"source\":\"\\u003ca href=\\\"http:\\/\\/twitter.com\\\" rel=\\\"nofollow\\\"\\u003eTwitter Web Client\\u003c\\/a\\u003e\",\"in_reply_to_status_id\":null,\"in_reply_to_status_id_str\":null,\"in_reply_to_user_id\":null,\"in_reply_to_user_id_str\":null,\"in_reply_to_screen_name\":null,\"user\":{\"id\":61592079,\"id_str\":\"61592079\",\"name\":\"Hank Green \\ud83d\\udc22\",\"screen_name\":\"hankgreen\",\"location\":\"Missoula, MT\",\"description\":\"I make videos. Co-creator of https:\\/\\/t.co\\/nAdRwz1GoJ, https:\\/\\/t.co\\/AxYpeXxCJc, and https:\\/\\/t.co\\/StIhHnlI51. New Dad! Nerdfighter. Montanan. T-Shirt Size: M\",\"url\":\"https:\\/\\/t.co\\/mmC5uxTjDW\",\"entities\":{\"url\":{\"urls\":[{\"url\":\"https:\\/\\/t.co\\/mmC5uxTjDW\",\"expanded_url\":\"http:\\/\\/www.youtube.com\\/vlogbrothers\",\"display_url\":\"youtube.com\\/vlogbrothers\",\"indices\":[0,23]}]},\"description\":{\"urls\":[{\"url\":\"https:\\/\\/t.co\\/nAdRwz1GoJ\",\"expanded_url\":\"http:\\/\\/Complexly.com\",\"display_url\":\"Complexly.com\",\"indices\":[29,52]},{\"url\":\"https:\\/\\/t.co\\/AxYpeXxCJc\",\"expanded_url\":\"http:\\/\\/DFTBA.com\",\"display_url\":\"DFTBA.com\",\"indices\":[54,77]},{\"url\":\"https:\\/\\/t.co\\/StIhHnlI51\",\"expanded_url\":\"http:\\/\\/VidCon.com\",\"display_url\":\"VidCon.com\",\"indices\":[83,106]}]}},\"protected\":false,\"followers_count\":759228,\"friends_count\":648,\"listed_count\":5461,\"created_at\":\"Thu Jul 30 20:36:34 +0000 2009\",\"favourites_count\":11605,\"utc_offset\":-21600,\"time_zone\":\"Mountain Time (US & Canada)\",\"geo_enabled\":true,\"verified\":true,\"statuses_count\":20989,\"lang\":\"en\",\"contributors_enabled\":false,\"is_translator\":false,\"is_translation_enabled\":false,\"profile_background_color\":\"4DB3C8\",\"profile_background_image_url\":\"http:\\/\\/pbs.twimg.com\\/profile_background_images\\/146466597\\/twitterbg2.jpg\",\"profile_background_image_url_https\":\"https:\\/\\/pbs.twimg.com\\/profile_background_images\\/146466597\\/twitterbg2.jpg\",\"profile_background_tile\":false,\"profile_image_url\":\"http:\\/\\/pbs.twimg.com\\/profile_images\\/668834117685874688\\/nAsJZ3j7_normal.png\",\"profile_image_url_https\":\"https:\\/\\/pbs.twimg.com\\/profile_images\\/668834117685874688\\/nAsJZ3j7_normal.png\",\"profile_banner_url\":\"https:\\/\\/pbs.twimg.com\\/profile_banners\\/61592079\\/1483211332\",\"profile_link_color\":\"E81C4F\",\"profile_sidebar_border_color\":\"858585\",\"profile_sidebar_fill_color\":\"C0DDE0\",\"profile_text_color\":\"000000\",\"profile_use_background_image\":true,\"has_extended_profile\":false,\"default_profile\":false,\"default_profile_image\":false,\"following\":null,\"follow_request_sent\":null,\"notifications\":null,\"translator_type\":\"none\"},\"geo\":null,\"coordinates\":null,\"place\":null,\"contributors\":null,\"is_quote_status\":false,\"retweet_count\":11,\"favorite_count\":152,\"favorited\":false,\"retweeted\":false,\"possibly_sensitive\":false,\"lang\":\"en\"},{\"created_at\":\"Wed Jul 26 21:48:27 +0000 2017\",\"id\":890327983461986305,\"id_str\":\"890327983461986305\",\"text\":\"@obsessingmuch @johngreen @mumblybee @Sharpie HHAHAHAHAH\",\"truncated\":false,\"entities\":{\"hashtags\":[],\"symbols\":[],\"user_mentions\":[{\"screen_name\":\"obsessingmuch\",\"name\":\"obsessingmuch\",\"id\":297702948,\"id_str\":\"297702948\",\"indices\":[0,14]},{\"screen_name\":\"johngreen\",\"name\":\"John Green\",\"id\":18055737,\"id_str\":\"18055737\",\"indices\":[15,25]},{\"screen_name\":\"mumblybee\",\"name\":\"mumblebethumps\",\"id\":2557466185,\"id_str\":\"2557466185\",\"indices\":[26,36]},{\"screen_name\":\"Sharpie\",\"name\":\"Sharpie\",\"id\":17498440,\"id_str\":\"17498440\",\"indices\":[37,45]}],\"urls\":[]},\"source\":\"\\u003ca href=\\\"http:\\/\\/twitter.com\\\" rel=\\\"nofollow\\\"\\u003eTwitter Web Client\\u003c\\/a\\u003e\",\"in_reply_to_status_id\":890266705225711620,\"in_reply_to_status_id_str\":\"890266705225711620\",\"in_reply_to_user_id\":297702948,\"in_reply_to_user_id_str\":\"297702948\",\"in_reply_to_screen_name\":\"obsessingmuch\",\"user\":{\"id\":61592079,\"id_str\":\"61592079\",\"name\":\"Hank Green \\ud83d\\udc22\",\"screen_name\":\"hankgreen\",\"location\":\"Missoula, MT\",\"description\":\"I make videos. Co-creator of https:\\/\\/t.co\\/nAdRwz1GoJ, https:\\/\\/t.co\\/AxYpeXxCJc, and https:\\/\\/t.co\\/StIhHnlI51. New Dad! Nerdfighter. Montanan. T-Shirt Size: M\",\"url\":\"https:\\/\\/t.co\\/mmC5uxTjDW\",\"entities\":{\"url\":{\"urls\":[{\"url\":\"https:\\/\\/t.co\\/mmC5uxTjDW\",\"expanded_url\":\"http:\\/\\/www.youtube.com\\/vlogbrothers\",\"display_url\":\"youtube.com\\/vlogbrothers\",\"indices\":[0,23]}]},\"description\":{\"urls\":[{\"url\":\"https:\\/\\/t.co\\/nAdRwz1GoJ\",\"expanded_url\":\"http:\\/\\/Complexly.com\",\"display_url\":\"Complexly.com\",\"indices\":[29,52]},{\"url\":\"https:\\/\\/t.co\\/AxYpeXxCJc\",\"expanded_url\":\"http:\\/\\/DFTBA.com\",\"display_url\":\"DFTBA.com\",\"indices\":[54,77]},{\"url\":\"https:\\/\\/t.co\\/StIhHnlI51\",\"expanded_url\":\"http:\\/\\/VidCon.com\",\"display_url\":\"VidCon.com\",\"indices\":[83,106]}]}},\"protected\":false,\"followers_count\":759228,\"friends_count\":648,\"listed_count\":5461,\"created_at\":\"Thu Jul 30 20:36:34 +0000 2009\",\"favourites_count\":11605,\"utc_offset\":-21600,\"time_zone\":\"Mountain Time (US & Canada)\",\"geo_enabled\":true,\"verified\":true,\"statuses_count\":20989,\"lang\":\"en\",\"contributors_enabled\":false,\"is_translator\":false,\"is_translation_enabled\":false,\"profile_background_color\":\"4DB3C8\",\"profile_background_image_url\":\"http:\\/\\/pbs.twimg.com\\/profile_background_images\\/146466597\\/twitterbg2.jpg\",\"profile_background_image_url_https\":\"https:\\/\\/pbs.twimg.com\\/profile_background_images\\/146466597\\/twitterbg2.jpg\",\"profile_background_tile\":false,\"profile_image_url\":\"http:\\/\\/pbs.twimg.com\\/profile_images\\/668834117685874688\\/nAsJZ3j7_normal.png\",\"profile_image_url_https\":\"https:\\/\\/pbs.twimg.com\\/profile_images\\/668834117685874688\\/nAsJZ3j7_normal.png\",\"profile_banner_url\":\"https:\\/\\/pbs.twimg.com\\/profile_banners\\/61592079\\/1483211332\",\"profile_link_color\":\"E81C4F\",\"profile_sidebar_border_color\":\"858585\",\"profile_sidebar_fill_color\":\"C0DDE0\",\"profile_text_color\":\"000000\",\"profile_use_background_image\":true,\"has_extended_profile\":false,\"default_profile\":false,\"default_profile_image\":false,\"following\":null,\"follow_request_sent\":null,\"notifications\":null,\"translator_type\":\"none\"},\"geo\":null,\"coordinates\":null,\"place\":null,\"contributors\":null,\"is_quote_status\":false,\"retweet_count\":0,\"favorite_count\":30,\"favorited\":false,\"retweeted\":false,\"lang\":\"tl\"}]")

(def stripe-json-string
  "{
  \"id\": \"cus_B87BG1Z4ePUWcM\",
  \"object\": \"customer\",
  \"account_balance\": 0,
  \"created\": 1501557732,
  \"currency\": \"usd\",
  \"default_source\": null,
  \"delinquent\": false,
  \"description\": null,
  \"discount\": null,
  \"email\": null,
  \"livemode\": false,
  \"metadata\": {
  },
  \"shipping\": null,
  \"sources\": {
    \"object\": \"list\",
    \"data\": [

    ],
    \"has_more\": false,
    \"total_count\": 0,
    \"url\": \"/v1/customers/cus_B87BG1Z4ePUWcM/sources\"
  },
  \"subscriptions\": {
    \"object\": \"list\",
    \"data\": [

    ],
    \"has_more\": false,
    \"total_count\": 0,
    \"url\": \"/v1/customers/cus_B87BG1Z4ePUWcM/subscriptions\"
  }
}")

(def twitter-json-string
  "{
    \"always_use_https\": true,
    \"discoverable_by_email\": true,
    \"geo_enabled\": true,
    \"language\": \"en\",
    \"protected\": false,
    \"screen_name\": \"theSeanCook\",
    \"show_all_inline_media\": false,
    \"sleep_time\": {
        \"enabled\": false,
        \"end_time\": null,
        \"start_time\": null
    },
    \"time_zone\": {
        \"name\": \"Pacific Time (US & Canada)\",
        \"tzinfo_name\": \"America/Los_Angeles\",
        \"utc_offset\": -28800
    },
    \"trend_location\": [
        {
            \"country\": \"United States\",
            \"countryCode\": \"US\",
            \"name\": \"Atlanta\",
            \"parentid\": 23424977,
            \"placeType\": {
                \"code\": 7,
                \"name\": \"Town\"
            },
            \"url\": \"http://where.yahooapis.com/v1/place/2357024\",
            \"woeid\": 2357024
        }
    ],
    \"use_cookie_personalization\": true,
    \"allow_contributor_request\": \"all\"
}")

(def json-string
  twitter-json-string)

(def youtube-atd-string
  "type date = {
  year : int;
  month : int;
  day : int;
}

type pageInfo  = {
  totalResults: int;
  resultsPerPage: int
}

(* Videos *)

type thumbnail = {
  url: string;
  width: int;
  height: int
}

type thumbnails = {
  default: thumbnail;
  medium: thumbnail;
  high: thumbnail;
  ?standard: thumbnail option;
  ?maxres: thumbnail option;
}

type localization = {
  title: string;
  description: string
}

type videoSnippet = {
  publishedAt: string;
  channelId: string;
  title: string;
  description: string;
  thumbnails: thumbnails;
  channelTitle: string;
  ~tags: string list;
  categoryId: string;
  liveBroadcastContent: string;
  localized: localization;
}

type statistics = {
  viewCount: int;
  ~likeCount: int;
  ~dislikeCount: int;
  ~favoriteCount: int;
  ~commentCount: int;
}

type contentDetails = {
  duration: string;
  dimension: string;
  definition: string;
  caption: string;
  licensedContent: bool;
  projection: string;
}

type status = {
  uploadStatus: string;
  privacyStatus: string;
  license: string;
  embeddable: bool;
  publicStatsViewable: bool;
}

type player = {
  embedHtml: string;
}

type topicDetails = {
  relevantTopicIds: string list;
  topicCategories: string list;
}

type videoItem = {
  kind: string;
  etag: string;
  id: string;
  ?contentDetails: contentDetails option;
  ?status: status option;
  ?player: player option;
  ?topicDetails: topicDetails option;
  ?snippet: videoSnippet option;
  ?statistics: statistics option;
}

type videoResult = {
  kind: string;
  etag: string;
  pageInfo: pageInfo;
  items: videoItem list;
}

(* Video Categories *)

type videoCategorySnippet = {
  channelId: string;
  title: string;
  assignable: bool;
}

type videoCategoryItem = {
  kind: string;
  etag: string;
  id: string;
  ?snippet: videoCategorySnippet option;
}

type videoCategoryResult = {
  kind: string;
  etag: string;
  items: videoCategoryItem list;
}

(* Channels *)

type channelThumbnail = {
  url: string
}

type channelThumbnails = {
  default: channelThumbnail;
  medium: channelThumbnail;
  high: channelThumbnail;
}

type channelSnippet = {
  title: string;
  description: string;
  ?customUrl: string option;
  publishedAt: string;
  thumbnails: channelThumbnails;
  localized: localization;
}

type channelRelatedPlaylists = {
  ?favorites: string option;
  uploads: string;
  watchHistory: string;
  watchLater: string;
}

type channelContentDetails = {
  relatedPlaylists: channelRelatedPlaylists;
}

type channelStatistics = {
  viewCount: string;
  commentCount: string;
  subscriberCount: string;
  hiddenSubscriberCount: bool;
  videoCount: string;
}

type channelTopicDetails = {
  topicIds: string list;
  topicCategories: string list;
}

type channelStatus = {
  privacyStatus: string;
  isLinked: bool;
  longUploadsStatus: string;
}

type channelBrandingChannel = {
  title: string;
  ?description: string option;
  ?keywords: string option;
  ?showRelatedChannels: bool option;
  ?showBrowseView: bool option;
  ?featuredChannelsTitle: string option;
  profileColor: string;
}

type channelBrandingImage = {
  ?bannerImageUrl: string option;
  ?bannerMobileImageUrl: string option;
  ?bannerTabletLowImageUrl: string option;
  ?bannerTabletImageUrl: string option;
  ?bannerTabletHdImageUrl: string option;
  ?bannerTabletExtraHdImageUrl: string option;
  ?bannerMobileLowImageUrl: string option;
  ?bannerMobileMediumHdImageUrl: string option;
  ?bannerMobileHdImageUrl: string option;
  ?bannerMobileExtraHdImageUrl: string option;
  ?bannerTvImageUrl: string option;
  ?bannerTvLowImageUrl: string option;
  ?bannerTvMediumImageUrl: string option;
  ?bannerTvHighImageUrl: string option;
}

type channelBrandingHint = 
  {
    property: string;
    value: string;
  }
         
       
type channelBrandingSettings = {
  channel: channelBrandingChannel;
  image: channelBrandingImage;
  hints: channelBrandingHint list;
}

type channelItem = {
  kind: string;
  etag: string;
  id: string;
  ?snippet: channelSnippet option;
  ?contentDetails: channelContentDetails option;
  ?statistics: channelStatistics option;
  ?topicDetails: channelTopicDetails option;
  ?status: channelStatus option;
  ?brandingSettings: channelBrandingSettings option;
}

type channelResult = {
  kind: string;
  etag: string;
  pageInfo: pageInfo;
  items: channelItem list;
}


type playlistSnippet = {
  publishedAt: string;
  channelId: string;
  title: string;
  description: string;
  thumbnails: thumbnails;
  channelTitle: string;
  ?defaultLanguage: string option;
  localized: localization
}

type playlistStatus = {
  privacyStatus: string;
}

type playlistContentDetails = {
  itemCount: int;
}

type playlistLocalizations = {
  ?en: localization option;
  ?eu: localization option;
  ?sv: localization option;
  ?hu: localization option;
  ?pt: localization option;
  ?lt: localization option;
  ?ko: localization option;
  ?zhTW <json name=\"zh-TW\">: localization option;
  ?ta: localization option;
  ?da: localization option;
  ?enXA <json name = \"en-XA\">: localization option;
  ?enGB <json name = \"en-GB\">: localization option;
  ?zhCN <json name = \"zh-CN\">: localization option;
  ?frCA <json name = \"fr-CA\">: localization option;
  ?es419<json name = \"es-419\">: localization option;
  ?ptPT <json name = \"pt-PT\">: localization option;
  ?zhHK <json name = \"zh-HK\">: localization option;
  ?cs: localization option;
  ?sw: localization option;
  ?vi: localization option;
  ?tr: localization option;
  ?bn: localization option;
  ?ja: localization option;
  ?fr: localization option;
  ?gu: localization option;
  ?kn: localization option;
  ?ar: localization option;
  ?af: localization option;
  ?no: localization option;
  ?bg: localization option;
  ?ml: localization option;
  ?gl: localization option;
  ?sk: localization option;
  ?uk: localization option;
  ?te: localization option;
  ?ru: localization option;
  ?fil: localization option;
  ?it: localization option;
  ?fa: localization option;
  ?zu: localization option;
  ?es: localization option;
  ?de: localization option;
  ?hr: localization option;
  ?mr: localization option;
  ?ms: localization option;
  ?ca: localization option;
  ?ro: localization option;
  ?sl: localization option;
  ?id: localization option;
  ?sr: localization option;
  ?el: localization option;
  ?lv: localization option;
  ?is: localization option;
  ?am: localization option;
  ?fi: localization option;
  ?th: localization option;
  ?ur: localization option;
  ?iw: localization option;
  ?hi: localization option;
  ?et: localization option;
  ?nl: localization option;
  ?pl: localization option;
}

type playlistItem = {
  kind: string;
  etag: string;
  id: string;
  ?snippet: playlistSnippet option;
  ?status: playlistStatus option;
  ?contentDetails: playlistContentDetails option;
  ?player: player option;
  ?localizations: playlistLocalizations option;
}

type playlistResult = {
  kind: string;
  etag: string;
  pageInfo: pageInfo;
  items: playlistItem list;
}

type playlistItemContentDetails = {
  videoId: string;
  videoPublishedAt: string;
}

type playlistItemSnippetResourceId = {
  kind: string;
  videoId: string;
}

type playlistItemSnippet = {
    publishedAt: string;
    channelId: string;
    title: string;
    description: string;
    thumbnails: thumbnails;
    channelTitle: string;
    playlistId: string;
    position: int;
    resourceId: playlistItemSnippetResourceId;
}

type playlistItemItem = {
  kind: string;
  etag: string;
  id: string;
  ?snippet: playlistItemSnippet option;
  ?status: playlistStatus option;
  ?contentDetails: playlistItemContentDetails option;
}

type playlistItemResult = {
  kind: string;
  etag: string;
  ?nextPageToken: string option;
  pageInfo: pageInfo;
  items: playlistItemItem list;
}

type searchItemId = {
    kind: string;
    ?videoId: string option;
    ?channelId: string option;
    ?playlistId: string option;
}
  
type searchItemSnippet = {
    publishedAt: string;
    channelId: string;
    title: string;
    description: string;
    thumbnails: thumbnails;
    channelTitle: string;
    liveBroadcastContent: string
}

type searchItem = {
  kind: string;
  etag: string;
  id: searchItemId;
  snippet: searchItemSnippet;
}

type searchResult = {
  kind: string;
  etag: string;
  ?nextPageToken: string option;
  ?prevPageToken: string option;
  regionCode: string;
  pageInfo: pageInfo;
  items: searchItem list
}

type commentSnippetAuthorId = {
  value: string;
}

type commentThreadItemCommentSnippet = {
  authorDisplayName: string;
  authorProfileImageUrl: string;
  authorChannelUrl: string;
  authorChannelId: commentSnippetAuthorId;
  videoId: string;
  textDisplay: string;
  textOriginal: string;
  canRate: bool;
  viewerRating: string;
  likeCount: int;
  publishedAt: string;
  updatedAt: string;
}

type commentThreadItemComment = {
  kind: string;
  etag: string;
  id: string;
  snippet: commentThreadItemCommentSnippet;
}

type commentThreadItemSnippet = {
  ?videoId: string option;
  topLevelComment: commentThreadItemComment;
  canReply: bool;
  totalReplyCount: int;
  isPublic: bool;
  ?replies: (commentThreadItemComment list) option;
}

type commentThreadItem = {
  kind: string;
  etag: string;
  id: string;
  snippet: commentThreadItemSnippet;
}

type commentThreadResult = {
  kind: string;
  etag: string;
  ?nextPageToken: string option;
  ?prevPageToken: string option;
  pageInfo: pageInfo;
  items: commentThreadItem list;
}
")

(def twitter-atd-string
  "type initialOauth = {
  token_type: string;
  access_token: string;
}

type userMention = {
  screen_name: string;
  name: string;
  id: int;
  id_str: string;
  indices: int list;
}

type url = {
  ?url: string option;
  expanded_url: string;
  display_url: string;
  indices: int list;
}

type hashtag = {
  text: string;
  indices: int list;
}

type entities = {
  hashtags: hashtag list;
  symbols: string list;
  user_mentions: userMention list;
  urls: url list;
}

type url2 = {
  urls: url list;
}

type description = {
  urls: url list;
}

type userEntities = {
  ?url: url2 option;
  description: description;
 }

type user = {
  id: int;
  id_str: string;
  name: string;
  screen_name: string;
  location: string;
  description: string;
  ?url: string option;
  entities: userEntities;
  protected: bool;
  followers_count: int;
  friends_count: int;
  created_at: string;
  favourites_count: int;
  ?utc_offset: int option;
  ?time_zone: string option;
  geo_enabled: bool;
  verified: bool;
  statuses_count: int;
  lang: string;
  contributors_enabled: bool;
  is_translator: bool;
  is_translation_enabled: bool;
  profile_background_color: string;
  ?profile_background_image_url: string option;
  ?profile_background_image_url_https: string option;
  profile_background_tile: bool;
  ?profile_image_url: string option;
  ?profile_image_url_https: string option;
  profile_link_color: string;
  profile_sidebar_border_color: string;
  profile_sidebar_fill_color: string;
  profile_text_color: string;
  profile_use_background_image: bool;
  has_extended_profile: bool;
  default_profile: bool;
  default_profile_image: bool;
  translator_type: string ;
}

type retweetedStatus = {
  created_at: string;
  id: int;
  id_str: string;
  text: string;
  truncated: bool;
  entities: entities;
  source: string;
  user: user;
  is_quote_status: bool;
  retweet_count: int;
  favorite_count: int;
  favorited: bool;
  retweeted: bool;
  lang: string;
}

type quotedStatus = {
  created_at: string;
  id: int;
  id_str: string;
  text: string;
  truncated: bool;
  entities: entities;
  source: string;
  user: user;
  is_quote_status: bool;
  retweet_count: int;
  favorite_count: int;
  favorited: bool;
  retweeted: bool;
  ?possibly_sensitive: bool option;
  lang: string;
}

type tweet = {
  created_at: string;
  id: int;
  id_str: string;
  text: string;
  truncated: bool;
  entities: entities;
  source: string;
  user: user;
  ?retweeted_status: retweetedStatus option;
  is_quote_status: bool;
  retweet_count: int;
  favorite_count: int;
  favorited: bool;
  retweeted: bool;
  lang: string;
  ?quoted_status_id_str: string option;
  ?quoted_status: quotedStatus option;
}

type timelineResult = tweet list
")

(def github-atd-string
  "type viewer = {
  id: string;
  login: string;
  ?email: string option;
  ?avatarUrl: string option;
  ?company: string option;
  ?location: string option;
  isViewer: bool;
  ?websiteUrl: string option;
  ?url: string option;
  name: string
}

type viewData = {
  viewer: viewer;
}

type userInfoData = {
  data: viewData;
}
")

(def google-atd-string
  "type userInfo = {
 sub: string;
 name: string;
 given_name: string;
 family_name: string;
 ?link: string option;
 ?profile: string option;
 picture: string;
 ?gender: string option;
 ?email: string option;
 ?email_verified: bool option;
 locale: string;
}

type invalidToken = {
 error: string;
 error_description: string;
}

type errorErrors = {
  message: string;
  location: string;
  domain: string;
  locationType: string;
  reason: string;
}

type error = {
  message: string;
  errors: errorErrors list;
  code: int;
}

type errorResult = {
  error: error;
}
")

(def test-atd-string
  "type sources = {
  object: string;
  url: string;
  has_more: bool;
  data: unknown list;
  total_count: int;
}

type subscriptions = {
  object: string;
  url: string;
  has_more: bool;
  data: unknown list;
  total_count: int;
}

type customer = {
  object: string;
  created: int;
  account_balance: int;
  ?discount: unknown option;
  currency: string;
  delinquent: bool;
  id: string;
  ?email: unknown option;
  ?default_source: unknown option;
  livemode: bool;
  ?shipping: unknown option;
  ?description: unknown option;
  sources: sources;
  metadata: metadata;
  subscriptions: subscriptions;
}
")
