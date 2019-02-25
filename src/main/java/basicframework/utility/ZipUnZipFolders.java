package basicframework.utility;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class ZipUnZipFolders {
	
	public static void Zipfolder(final Path sourcefolder,Path zipPath) throws IOException{
		
		
		final ZipOutputStream zos= new ZipOutputStream(new FileOutputStream(zipPath.toFile()));
		Files.walkFileTree(sourcefolder, new SimpleFileVisitor<Path>(){
			public FileVisitResult visitFile(Path file,BasicFileAttributes attrs) throws IOException{
				zos.putNextEntry(new ZipEntry(sourcefolder.relativize(file).toString()));
				Files.copy(file, zos);
				zos.closeEntry();
				return FileVisitResult.CONTINUE;	
			}
		});
		zos.close();
	}

}
