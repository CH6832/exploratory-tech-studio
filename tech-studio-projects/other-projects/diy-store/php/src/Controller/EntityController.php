<?php

// src/Controller/EntityController.php

namespace App\Controller;

use App\Entity\SomeEntity;
use Doctrine\ORM\EntityManagerInterface;
use Symfony\Bundle\FrameworkBundle\Controller\AbstractController;
use Symfony\Component\HttpFoundation\Response;
use Symfony\Component\Routing\Annotation\Route;

class EntityController extends AbstractController
{
    private $entityManager;

    public function __construct(EntityManagerInterface $entityManager)
    {
        $this->entityManager = $entityManager;
    }

    #[Route('/entities', name: 'entities')]
    public function index(): Response
    {
        $repository = $this->entityManager->getRepository(SomeEntity::class);
        $entities = $repository->findAll();

        return $this->render('entity/index.html.twig', [
            'entities' => $entities,
        ]);
    }
}

?>
