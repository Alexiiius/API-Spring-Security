package com.alex.APISECURITY;

import com.alex.APISECURITY.models.PermissionEntity;
import com.alex.APISECURITY.models.RoleEntity;
import com.alex.APISECURITY.models.RoleEnum;
import com.alex.APISECURITY.models.UserEntity;
import com.alex.APISECURITY.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.List;
import java.util.Set;

@SpringBootApplication
public class ApiSecurityApplication {

	public static void main(String[] args) {
		SpringApplication.run(ApiSecurityApplication.class, args);
	}


	@Bean
	CommandLineRunner init(UserRepository repository) {
		return args-> {

			//CREATE PERMISSIONS

			PermissionEntity createPermission = PermissionEntity.builder()
					.name("CREATE")
					.build();
			PermissionEntity readPermission = PermissionEntity.builder()
					.name("READ")
					.build();
			PermissionEntity updatePermission = PermissionEntity.builder()
					.name("UPDATE")
					.build();
			PermissionEntity deletePermission = PermissionEntity.builder()
					.name("DELETE")
					.build();
			PermissionEntity developerPermission = PermissionEntity.builder()
					.name("DEVELOPER")
					.build();


			//CREATE ROLES

			RoleEntity roleAdmin = RoleEntity.builder()
					.RoleEnum(RoleEnum.ADMIN)
					.permissionsSet(Set.of(createPermission, readPermission, updatePermission, deletePermission))
					.build();
			RoleEntity roleUser = RoleEntity.builder()
					.RoleEnum(RoleEnum.USER)
					.permissionsSet(Set.of(readPermission, createPermission))
					.build();
			RoleEntity roleInvited = RoleEntity.builder()
					.RoleEnum(RoleEnum.INVITED)
					.permissionsSet(Set.of(readPermission))
					.build();
			RoleEntity roleDeveloper = RoleEntity.builder()
					.RoleEnum(RoleEnum.DEVELOPER)
					.permissionsSet(Set.of(createPermission, readPermission, updatePermission, deletePermission, developerPermission))
					.build();


			// CREATE USERS

			UserEntity userAlex = UserEntity.builder()
					.username("Alex")
					.password("$2a$10$cuQe36X0z71tSgp.MfSpSu7SP.KuK6BByPC1AsMqi/PzABSX/UvR6")
					.isEnabled(true)
					.accountNoExpired(true)
					.accountNoExpired(true)
					.accountNoLocked(true)
					.credentialNoExpired(true)
					.roles(Set.of(roleDeveloper))
					.build();
			UserEntity userDani = UserEntity.builder()
					.username("Dani")
					.password("$2a$10$cuQe36X0z71tSgp.MfSpSu7SP.KuK6BByPC1AsMqi/PzABSX/UvR6")
					.isEnabled(true)
					.accountNoExpired(true)
					.accountNoExpired(true)
					.accountNoLocked(true)
					.credentialNoExpired(true)
					.roles(Set.of(roleAdmin))
					.build();
			UserEntity userApplication = UserEntity.builder()
					.username("Application")
					.password("$2a$10$cuQe36X0z71tSgp.MfSpSu7SP.KuK6BByPC1AsMqi/PzABSX/UvR6")
					.isEnabled(true)
					.accountNoExpired(true)
					.accountNoExpired(true)
					.accountNoLocked(true)
					.credentialNoExpired(true)
					.roles(Set.of(roleUser))
					.build();
			UserEntity userInvited = UserEntity.builder()
					.username("Invited")
					.password("$2a$10$cuQe36X0z71tSgp.MfSpSu7SP.KuK6BByPC1AsMqi/PzABSX/UvR6")
					.isEnabled(true)
					.accountNoExpired(true)
					.accountNoExpired(true)
					.accountNoLocked(true)
					.credentialNoExpired(true)
					.roles(Set.of(roleInvited))
					.build();

			repository.saveAll(List.of(userAlex, userDani, userApplication, userInvited));


		};
	}

}
