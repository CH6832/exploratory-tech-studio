<?php

// tests/Unit/TaskTest.php

namespace Tests\Unit;

use Tests\TestCase;
use Illuminate\Foundation\Testing\RefreshDatabase;
use App\Models\Task;

class TaskTest extends TestCase
{
    use RefreshDatabase;

    /** @test */
    public function it_can_create_task()
    {
        $task = Task::factory()->create([
            'title' => 'Test Task',
            'description' => 'Test description'
        ]);

        $this->assertInstanceOf(Task::class, $task);
        $this->assertEquals('Test Task', $task->title);
        $this->assertEquals('Test description', $task->description);
    }
}
