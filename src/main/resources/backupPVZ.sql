CREATE TABLE characters
(
id_character serial,
name varchar(1000),
image varchar(1000),
health varchar(1000),
variant varchar(1000),
primary_weapon varchar(1000),
weapon_damage varchar(1000),
abilities varchar(1000),
fps_class varchar(1000),
primary key(id_character));


CREATE TABLE variants
(
id_variant serial,
name varchar(1000),
image varchar(1000),
health varchar(1000),
primary_weapon varchar(1000),
weapon_damage varchar(1000),
variant_perk varchar(1000),
rarity varchar(1000),
primary key(id_variant));
