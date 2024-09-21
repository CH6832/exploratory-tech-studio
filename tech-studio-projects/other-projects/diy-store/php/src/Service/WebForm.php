<?php

// src/Service/WebForm.php

namespace App\Service;

use Symfony\Component\HttpFoundation\Session\SessionInterface;
use Symfony\Component\HttpFoundation\RequestStack;
use Symfony\Component\HttpFoundation\Response;

class WebForm
{
    private $formTemplate; // filename
    private $fieldsTemplate; // filename
    private $data; // Array
    private $notRequired; // Array
    private $requestStack;

    public function __construct(string $formTemplate, string $fieldsTemplate, array $data = null, RequestStack $requestStack)
    {
        if (!is_string($formTemplate) || !is_string($fieldsTemplate)) {
            throw new \InvalidArgumentException("The first 2 parameters must be filenames.");
        }

        $this->formTemplate = $formTemplate;
        $this->fieldsTemplate = $fieldsTemplate;
        $this->data = $data ?? [];
        $this->requestStack = $requestStack;
    }

    public function setFieldsNotRequired(array $notRequired)
    {
        $this->notRequired = $notRequired;
    }

    public function displayForm(): Response
    {
        $data = $this->data;
        $fields = $this->fieldsTemplate;
        $form = $this->formTemplate;

        return new Response($this->renderForm($data, $fields, $form));
    }

    private function renderForm(array $data, string $fieldsTemplate, string $formTemplate): string
    {
        ob_start();
        extract($data);
        include($fieldsTemplate);
        include($formTemplate);
        return ob_get_clean();
    }

    public function getAllFields(): array
    {
        return $this->data;
    }

    public function checkForBlanks(): array
    {
        if (empty($this->data)) {
            throw new \RuntimeException("No form data.");
        }

        $blanks = [];
        foreach ($this->data as $key => $value) {
            if (empty($value) && !in_array($key, $this->notRequired ?? [])) {
                $blanks[] = $key;
            }
        }

        return $blanks;
    }

    public function verifyData(): array
    {
        if (empty($this->data)) {
            throw new \RuntimeException("No form data.");
        }

        $errors = [];
        foreach ($this->data as $key => $value) {
            if (!empty($value)) {
                $result = $this->validateField($key, $value);
                if ($result !== true) {
                    $errors[$key] = $result;
                }
            }
        }

        return $errors;
    }

    private function validateField(string $key, $value)
    {
        if (preg_match("/name/", $key) && !preg_match("/user/", $key)) {
            return $this->checkName($value);
        }

        if (preg_match("/addr|street|city/", $key)) {
            return $this->checkAddress($value);
        }

        if (preg_match("/email/", $key)) {
            return $this->checkEmail($value);
        }

        if (preg_match("/phone|fax/", $key)) {
            return $this->checkPhone($value);
        }

        if (preg_match("/zip/", $key)) {
            return $this->checkZip($value);
        }

        return true;
    }

    public function trimData()
    {
        $this->data = array_map('trim', $this->data);
    }

    public function stripTagsFromData()
    {
        $this->data = array_map('strip_tags', $this->data);
    }

    private function checkName($field)
    {
        if (!preg_match("/^[A-Za-z' -]{1,50}$/", $field)) {
            return "$field is not a valid name. Please try again.";
        }
        return true;
    }

    private function checkAddress($field)
    {
        if (!preg_match("/^[A-Za-z0-9.,' -]{1,50}$/", $field)) {
            return "$field is not a valid address. Please try again.";
        }
        return true;
    }

    private function checkZip($field)
    {
        if (!preg_match("/^[0-9]{5}(?:-[0-9]{4})?$/", $field)) {
            return "$field is not a valid ZIP code. Please try again.";
        }
        return true;
    }

    private function checkPhone($field)
    {
        if (!preg_match("/^[0-9Xx -]{7,20}$/", $field)) {
            return "$field is not a valid phone number. Please try again.";
        }
        return true;
    }

    private function checkEmail($field)
    {
        if (!preg_match("/^.+@.+\..+$/", $field)) {
            return "$field is not a valid email address. Please try again.";
        }
        return true;
    }
}

?>
