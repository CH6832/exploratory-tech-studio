<?php

namespace App\Session;

interface SessionInterface
{
    public function start();

    public function getId(): string;

    public function setId(string $id);

    public function getName(): string;

    public function setName(string $name);

    public function invalidate(int $lifetime = 0): bool;

    public function migrate(bool $destroy = false, int $lifetime = 0): bool;

    public function save();

    public function has(string $name): bool;

    public function get(string $name, $default = null);

    public function set(string $name, $value);

    public function all(): array;

    public function replace(array $attributes);

    public function remove(string $name);

    public function clear();
}

?>
