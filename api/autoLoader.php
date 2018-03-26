<?php
/**
 * Created by PhpStorm.
 * User: Wouter Kodde
 * Date: 12-10-2016
 * Time: 20:35
 */
function loadDirectory($dir)
{
    $list = scandir($dir);
    foreach($list as $item)
    {
        if($item != '.' && $item != '..')
        {
            if(is_dir($dir.'/'.$item))
            {
                loadDirectory($dir.'/'.$item);
            }
            else
            {
                if(file_exists($dir.DIRECTORY_SEPARATOR.$item))
                {
                    require ($dir.DIRECTORY_SEPARATOR.$item);
                } else {echo "no!";};
            }
        }
    }
}
loadDirectory(__DIR__.DIRECTORY_SEPARATOR.'Classes' . DIRECTORY_SEPARATOR);