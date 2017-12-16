-- --------------------------------------------------------
-- Хост:                         build.huesos228.com
-- Версия сервера:               5.5.55-0+deb8u1 - (Debian)
-- Операционная система:         debian-linux-gnu
-- HeidiSQL Версия:              9.4.0.5125
-- --------------------------------------------------------

-- Дамп структуры базы данных core

-- Дамп структуры для таблица core.authentication
CREATE TABLE IF NOT EXISTS `authentication` (
  `id` int(11) NOT NULL,
  `password` text NOT NULL,
  `registration_ip` varchar(16) NOT NULL,
  `email` varchar(64) NOT NULL,
  `email_confirmed` tinyint(1) NOT NULL,
  `email_confirmation_code` varchar(16) NOT NULL,
  `email_code_timestamp` bigint(18) NOT NULL,
  `last_authenticated` bigint(18) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


-- -- Дамп структуры для таблица core.blocking_ip
-- CREATE TABLE IF NOT EXISTS `blocking_ip` (
--   `ip` varchar(16) NOT NULL,
--   `blocked_until` bigint(18) NOT NULL
-- ) ENGINE=InnoDB DEFAULT CHARSET=utf8;


-- Дамп структуры для таблица core.custom_prefixes
CREATE TABLE IF NOT EXISTS `custom_prefixes` (
  `id` int(11) NOT NULL,
  `prefix` varchar(16) NOT NULL DEFAULT '',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


-- Дамп структуры для таблица core.friends
CREATE TABLE IF NOT EXISTS `friends` (
  `id` int(11) NOT NULL,
  `friend_id` int(11) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


-- Дамп структуры для таблица core.identifier
CREATE TABLE IF NOT EXISTS `identifier` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `player_name` varchar(16) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `player_name` (`player_name`)
) ENGINE=InnoDB AUTO_INCREMENT=21 DEFAULT CHARSET=utf8;

INSERT INTO `identifier` (`id`, `player_name`) VALUES
	(1, 'kvlt');

-- Дамп структуры для таблица core.ignores
CREATE TABLE IF NOT EXISTS `ignores` (
  `id` int(11) NOT NULL,
  `ignored_id` int(11) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


-- Дамп структуры для таблица core.infractions
CREATE TABLE IF NOT EXISTS `infractions` (
  `id` int(11) NOT NULL,
  `mute_end` bigint(18) NOT NULL DEFAULT '0',
  `mute_reason` varchar(128) NOT NULL DEFAULT '',
  `mute_enforcer` varchar(16) NOT NULL DEFAULT '',
  `mutes` int(11) NOT NULL DEFAULT '0',
  `ban_end` bigint(18) NOT NULL DEFAULT '0',
  `ban_reason` varchar(128) NOT NULL DEFAULT '',
  `ban_enforcer` varchar(16) NOT NULL DEFAULT '',
  `bans` int(11) NOT NULL DEFAULT '0',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


-- Дамп структуры для таблица core.join_info
CREATE TABLE IF NOT EXISTS `join_info` (
  `id` int(11) NOT NULL,
  `ip` varchar(16) NOT NULL DEFAULT 'unknown',
  `server` varchar(32) NOT NULL DEFAULT 'unknown',
  `online_time` bigint(18) NOT NULL DEFAULT '0',
  `last_online` bigint(18) NOT NULL DEFAULT '0',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


-- Дамп структуры для таблица core.players
CREATE TABLE IF NOT EXISTS `players` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(32) NOT NULL,
  `password` varchar(32) DEFAULT NULL,
  `group` varchar(32) DEFAULT NULL,
  `last_ip` varchar(16) DEFAULT NULL,
  `last_join` varchar(16) DEFAULT NULL,
  `last_server` varchar(16) DEFAULT NULL,
  `muted_by` varchar(32) DEFAULT NULL,
  `banned_by` varchar(32) DEFAULT NULL,
  `is_banned` tinyint(1) NOT NULL DEFAULT '0',
  `is_muted` tinyint(1) NOT NULL DEFAULT '0',
  `mute_amount` tinyint(4) NOT NULL DEFAULT '0',
  `ban_amount` tinyint(4) NOT NULL DEFAULT '0',
  `uuid` varchar(64) NOT NULL,
  `total_time` timestamp NULL DEFAULT NULL,
  `banned_until` timestamp NULL DEFAULT NULL,
  `muted_until` timestamp NULL DEFAULT NULL,
  `ban_reason` varchar(128) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `id` (`id`),
  UNIQUE KEY `name` (`name`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

INSERT INTO `players` (`id`, `name`, `password`, `group`, `last_ip`, `last_join`, `last_server`, `muted_by`, `banned_by`, `is_banned`, `is_muted`, `mute_amount`, `ban_amount`, `uuid`, `total_time`, `banned_until`, `muted_until`, `ban_reason`) VALUES
	(3, 'kvlt', NULL, NULL, NULL, NULL, NULL, NULL, NULL, 0, 0, 0, 0, '6d838175-cecb-394f-a208-c63b5753769e', NULL, NULL, NULL, NULL);

-- Дамп структуры для таблица core.players_groups
CREATE TABLE IF NOT EXISTS `players_groups` (
  `id` int(11) NOT NULL,
  `group_id` int(11) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


-- Дамп структуры для таблица core.premium_auth
CREATE TABLE IF NOT EXISTS `premium_auth` (
  `player_name` varchar(16) NOT NULL,
  `using_premium_auth` tinyint(1) NOT NULL DEFAULT '0',
  PRIMARY KEY (`player_name`),
  UNIQUE KEY `player_name` (`player_name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- Дамп структуры для таблица core.reporters
CREATE TABLE IF NOT EXISTS `reporters` (
  `id` int(11) NOT NULL,
  `total_reports` int(11) NOT NULL DEFAULT '0',
  `reports_approved` int(11) NOT NULL DEFAULT '0',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


-- Дамп структуры для таблица core.selected_skins
CREATE TABLE IF NOT EXISTS `selected_skins` (
  `id` int(11) NOT NULL,
  `skin` varchar(16) NOT NULL DEFAULT '',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


-- Дамп структуры для таблица core.settings
CREATE TABLE IF NOT EXISTS `settings` (
  `id` int(11) NOT NULL,
  `setting_id` int(11) NOT NULL,
  `setting_value` int(11) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


-- Дамп структуры для таблица core.skins_storage
CREATE TABLE IF NOT EXISTS `skins_storage` (
  `skin` varchar(16) NOT NULL,
  `update_time` bigint(18) NOT NULL,
  `value` text NOT NULL,
  `signature` text NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


-- Дамп структуры для таблица core.test
CREATE TABLE IF NOT EXISTS `test` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(50) DEFAULT '0',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;
